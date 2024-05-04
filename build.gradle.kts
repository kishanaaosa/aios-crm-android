// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "8.3.2" apply false
    id ("com.android.library") version "8.3.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.5.0" apply false
    id ("com.google.dagger.hilt.android") version "2.45" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.firebase.crashlytics") version "2.8.1" apply false
    id("com.google.firebase.firebase-perf") version "1.4.1" apply false
}