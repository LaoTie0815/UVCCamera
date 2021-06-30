# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)

# 设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../../jniLibs/${CMAKE_ANDROID_ARCH_ABI})

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../libusb)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../../libusb/os)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../)

add_definitions(-D ACCESS_RAW_DESCRIPTORS)

add_library( # Sets the name of the library.
        usb100
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ../../libusb/core.c
        ../../libusb/descriptor.c
        ../../libusb/hotplug.c
        ../../libusb/io.c
        ../../libusb/sync.c
        ../../libusb/strerror.c
        ../../libusb/os/android_usbfs.c
        ../../libusb/os/poll_posix.c
        ../../libusb/os/threads_posix.c
        ../../libusb/os/android_netlink.c
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
target_link_libraries(usb100
        ${log-lib})