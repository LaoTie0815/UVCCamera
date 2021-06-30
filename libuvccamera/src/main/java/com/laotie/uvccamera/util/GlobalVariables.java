package com.laotie.uvccamera.util;


import com.laotie.uvccamera.USBMonitor;

/**
 * @Version V1.0.0
 * Author:Created by LaoTie on 2020/5/7.
 **/
public class GlobalVariables {

    /**
     * 为了防止出现超过900多次打开关闭uvccamera，而出现应用崩溃问题(Too many open files), 这里存储这个全局变量，而不每次打开uvccamera都去打开新建一处
     */
    private USBMonitor.UsbControlBlock usbControlBlock;

    private GlobalVariables(){

    }

    private static class GlobalVariablesHolder{
        private static final GlobalVariables instance = new GlobalVariables();
    }

    public static  final  GlobalVariables getInstance(){
        return GlobalVariablesHolder.instance;
    }

    public USBMonitor.UsbControlBlock getUsbControlBlock() {
        return usbControlBlock;
    }

    public void setUsbControlBlock(USBMonitor.UsbControlBlock usbControlBlock) {
        this.usbControlBlock = usbControlBlock;
    }
}
