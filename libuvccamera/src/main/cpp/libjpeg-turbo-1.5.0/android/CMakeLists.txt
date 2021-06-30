# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.


cmake_minimum_required(VERSION 3.4.1)


# 设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI})

set(CMAKE_VERBOSE_MAKEFILE on)

#SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wl,--allow-shlib-undefined")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/../simd)

if (${CMAKE_ANDROID_ARCH_ABI} MATCHES "arm64-v8a") #针对不同 ABI 设置不同的编译代码 或 if(${ANDROID_ABI} STREQUAL "areambi")
    #    aux_source_directory(../simd/jsimd_arm64.c JSIMD_ARM)
    #    aux_source_directory(../simd/jsimd_arm64_neon.S JSIMD_ARM_NEON)
    enable_language(ASM)
    SET(JSIMD_ARM ../simd/jsimd_arm64.c)
    SET(JSIMD_ARM_NEON ../simd/jsimd_arm64_neon.S)
    #    set(JSIMD_ARM ${PROJECT_SOURCE_DIR}/libuvccamera/src/main/jni/libjpeg-turbo-1.5.0/simd/jsimd_arm64.c)
    #    set(JSIMD_ARM_NEON ${PROJECT_SOURCE_DIR}/libuvccamera/src/main/jni/libjpeg-turbo-1.5.0/simd/jsimd_arm64_neon.S)
    add_definitions(-D SIZEOF_SIZE_T=4)
    message("this is arm64-v8a")
elseif (${CMAKE_ANDROID_ARCH_ABI} MATCHES "armeabi-v7a")
    #    aux_source_directory(../simd/jsimd_arm.c JSIMD_ARM)
    #    aux_source_directory(../simd/jsimd_arm_neon.S JSIMD_ARM_NEON)
    enable_language(ASM)
    SET(JSIMD_ARM ../simd/jsimd_arm.c)
    SET(JSIMD_ARM_NEON ../simd/jsimd_arm_neon.S)
#    set(JSIMD_ARM ${PROJECT_SOURCE_DIR}/libuvccamera/src/main/jni/libjpeg-turbo-1.5.0/simd/jsimd_arm.c.c)
#    set(JSIMD_ARM_NEON ${PROJECT_SOURCE_DIR}/libuvccamera/src/main/jni/libjpeg-turbo-1.5.0/simd/jsimd_arm_neon.S.S)
    add_definitions(-D SIZEOF_SIZE_T=8)
    message("this is armeabi-v7a")
endif ()

#if (${CMAKE_ANDROID_NDK_TOOLCHAIN_VERSION} MATCHES clang) #根据交叉编译链版本
#    aux_source_directory(../jsimd_none.c JSIMD)
#    message("MATCHES clang")
#else()
#    aux_source_directory(../simd/jsimd_mips.c JSIMD)
#    aux_source_directory(../simd/jsimd_mips_dspr2.c JSIMD)
#endif ()

add_library( # Sets the name of the library.
        jpeg-turbo1500
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ../jcapimin.c
        ../jcapistd.c
        ../jccoefct.c
        ../jccolor.c
        ../jcdctmgr.c
        ../jchuff.c
        ../jcinit.c
        ../jcmainct.c
        ../jcmarker.c
        ../jcmaster.c
        ../jcomapi.c
        ../jcparam.c
        ../jcphuff.c
        ../jcprepct.c
        ../jcsample.c
        ../jctrans.c
        ../jdapimin.c
        ../jdapistd.c
        ../jdatadst.c
        ../jdatasrc.c
        ../jdcoefct.c
        ../jdcolor.c
        ../jddctmgr.c
        ../jdhuff.c
        ../jdinput.c
        ../jdmainct.c
        ../jdmarker.c
        ../jdmaster.c
        ../jdmerge.c
        ../jdphuff.c
        ../jdpostct.c
        ../jdsample.c
        ../jdtrans.c
        ../jerror.c
        ../jfdctflt.c
        ../jfdctfst.c
        ../jfdctint.c
        ../jidctflt.c
        ../jidctfst.c
        ../jidctint.c
        ../jidctred.c
        ../jquant1.c
        ../jquant2.c
        ../jutils.c
        ../jmemmgr.c
        ../jmemnobs.c

        ../jaricom.c
        ../jcarith.c
        ../jdarith.c

        ../turbojpeg.c
        ../transupp.c
        ../jdatadst-tj.c
        ../jdatasrc-tj.c

        #                ../simd/jsimd_mips.c
        #                ../simd/jsimd_mips_dspr2.S
        #                jsimd_none.c


        #        ${JSIMD}
        ${JSIMD_ARM}
        ${JSIMD_ARM_NEON}
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
target_link_libraries(jpeg-turbo1500
        ${log-lib})

