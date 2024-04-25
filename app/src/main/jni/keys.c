#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_ecommercwebsite_aioscrm_di_NetworkModule_getDevBaseUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "aHR0cHM6Ly9zdGFnaW5nLWFwaS5kZWFsZXJiYXp6YXIuY29tL2FwaS92MS8=");
}

JNIEXPORT jstring JNICALL
Java_com_ecommercwebsite_aioscrm_di_NetworkModule_getProdBaseUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "aHR0cHM6Ly9hcGkuZGVhbGVyYmF6emFyLmNvbS9hcGkvdjEv");
}

JNIEXPORT jstring JNICALL
Java_com_ecommercwebsite_aioscrm_BaseApp_getTermsOfServiceUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "aHR0cHM6Ly9kZWFsZXJiYXp6YXIuY29tL2xlZ2FsL3Rlcm1zLW9mLXVzZQ==");
}

JNIEXPORT jstring JNICALL
Java_com_ecommercwebsite_aioscrm_BaseApp_getPrivacyPolicyUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "aHR0cHM6Ly9kZWFsZXJiYXp6YXIuY29tL2xlZ2FsL3ByaXZhY3ktcG9saWN5");
}

JNIEXPORT jstring JNICALL
Java_com_ecommercwebsite_aioscrm_BaseApp_getAboutUsUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "aHR0cHM6Ly9kZWFsZXJiYXp6YXIuY29t");
}