package com.softwareallin1.aioscrm.ui.attendance.ui

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.DialogFragmentBase
import com.softwareallin1.aioscrm.databinding.FragmentFillAttendanceBinding
import com.softwareallin1.aioscrm.network.ResponseData
import com.softwareallin1.aioscrm.network.ResponseHandler
import com.softwareallin1.aioscrm.ui.attendance.model.FillAttendanceResponse
import com.softwareallin1.aioscrm.ui.attendance.viewmodel.FillAttendanceViewModel
import com.softwareallin1.aioscrm.utils.FillUtils
import com.softwareallin1.aioscrm.utils.LocationProvider
import com.softwareallin1.aioscrm.utils.permissions.Permission
import com.softwareallin1.aioscrm.utils.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.quality
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class FillAttendanceFragment :
    DialogFragmentBase<FillAttendanceViewModel, FragmentFillAttendanceBinding>() {
    private val permissionManager = PermissionManager.from(this)
    private var imageUri: Uri? = null

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                //val imageUri: Uri? = result.data?.data
                //  val uri = Uri.fromFile(File(imageUri?.path))
                /*  viewModel.imageFile = imageUri?.let {
                      FillUtils.getPathFromUri(requireContext(), it)
                          ?.let { File(it) }
                  }*/

                /* viewModel.imageFile = imageUri?.let {
                     FillUtils.getPathFromUri(requireContext(), it)
                         ?.let { FillUtils.compressOriginalImage(requireContext(), it) }
                 }?.let { File(it) }
 */
                val actualImageFile = imageUri?.let {
                    FillUtils.getPathFromUri(requireContext(), it)
                        ?.let { File(it) }
                }

                lifecycleScope.launch {
                    viewModel.imageFile =
                        actualImageFile?.let {
                            Compressor.compress(requireContext(), it) {
                                quality(80) // Set desired image quality (0-100)
                                default()
                            }
                        }
                }
                getDataBinding().ivProfile.setImageURI(imageUri)
            } else {
                viewModel.showSnackbarMessage("Image capture failed")
                // Toast.makeText(context, "Image capture failed", Toast.LENGTH_SHORT).show()
            }
        }


    override fun getLayoutId() = R.layout.fragment_fill_attendance

    override fun setupToolbar() {
        /*  (activity as MainActivity).setStatusBarColor(R.color.white, true)
          viewModel.setToolbarItems(
              ToolbarModel(
                  isVisible = true,
                  title = "Attendance",
                  isBackButtonVisible = true,
                  isBottomNavVisible = false,
                  isNotificationVisible = false,
                  isMenuVisible = false
              )
          )*/
    }

    override fun initializeScreenVariables() {
        getDataBinding().viewModel = viewModel
        viewModel.initVariables()
        checkPermissionsAndGetLocation()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.onTakePhotoClick.observe(this) {
            checkPermissionAndOpenCamera()
        }
        viewModel.onFillAttendanceClick.observe(this) {
            if (viewModel.imageFile != null) {
                viewModel.fillAttendance()
            } else {
                viewModel.showSnackbarMessage("Please take photo")
            }
        }
        viewModel.fillAttendanceResponse.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            when (it) {
                is ResponseHandler.Empty -> {

                }
                is ResponseHandler.Loading -> {
                    viewModel.showProgressBar(true)
                }

                is ResponseHandler.OnFailed -> {
                    viewModel.showProgressBar(false)
                    httpFailedHandler(it.code, it.message, it.messageCode)
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<FillAttendanceResponse>?> -> {
                    viewModel.showProgressBar(false)
                    viewModel.showSnackbarMessage(it.response?.meta?.message.toString())
                    dialog?.dismiss()
                }
            }
        })
    }

    private fun checkPermissionAndOpenCamera() {
        permissionManager.request(
            Permission.Camera
        ).rationale(getString(R.string.permission_location)).checkDetailedPermission { result ->
            if (result.all { it.value }) {
                openCamera()
            }
        }
    }

    private fun openCamera() {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "New Picture")
            put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                "Pictures/AIOS-CRM"
            ) // Optional, but recommended.
        }
        imageUri = activity?.contentResolver?.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        // Specify the camera to be used (0 for rear camera, 1 for front camera)
        cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CAMERA_FACING_FRONT)

        // Disable the camera switch button
        cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", false)


        takePictureLauncher.launch(cameraIntent)
    }

    private fun checkPermissionsAndGetLocation() {
        permissionManager.request(
            Permission.Location
        ).rationale(getString(R.string.permission_location)).checkDetailedPermission { result ->
            if (result.all { it.value }) {
                getLocation()
                // viewModel.showSnackbarMessage("Permission Granted")
            } else {
                // viewModel.showSnackbarMessage("Permission Denied")
            }
        }
    }

    private fun getLocation() {
        val locationProvider = LocationProvider(requireContext())

        locationProvider.getLastKnownLocation { location ->
            if (location != null) {
                viewModel.lat.value = location.latitude.toString()
                viewModel.long.value = location.longitude.toString()
                getDataBinding().tvLocation.text =
                    "Location: ${viewModel.lat.value}, ${viewModel.long.value}"
            } else {
                viewModel.showSnackbarMessage("Failed to take location")
            }
        }
    }

    override fun getViewModelClass(): Class<FillAttendanceViewModel> =
        FillAttendanceViewModel::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false

}