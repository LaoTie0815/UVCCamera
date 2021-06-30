# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)

#add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/../libusb/android/jni)
#add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/../libuvc/android/jni)

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../rapidjson/include)

#add_library(usb100_test SHARED IMPORTED )
#set_target_properties(
#        # Specifies the target library.
#        usb100_test
#        # Specifies the parameter you want to define.
#        PROPERTIES IMPORTED_LOCATION
#        # Provides the path to the library you want to import.
#        "${CMAKE_CURRENT_SOURCE_DIR}/../libusb/jniLibs/${ANDROID_ABI}/libusb100_test.so")
#
#add_library(uvc SHARED IMPORTED )
#set_target_properties(
#        # Specifies the target library.
#        uvc
#        # Specifies the parameter you want to define.
#        PROPERTIES IMPORTED_LOCATION
#        # Provides the path to the library you want to import.
#        "${CMAKE_CURRENT_SOURCE_DIR}/../libuvc/jniLibs/${ANDROID_ABI}/libuvc.so")

#add_subdirectory(../libjpeg-turbo-1.5.0/android)
#add_subdirectory(../libusb/android/jni)
#add_subdirectory(../libuvc/android/jni)

add_library( # Sets the name of the library.
        UVCCamera
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        #        ../utilbase.cpp
        UVCCamera.cpp
        UVCPreview.cpp
        UVCButtonCallback.cpp
        UVCStatusCallback.cpp
        Parameters.cpp
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
find_library(log-lib
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries(UVCCamera
        ${log-lib})