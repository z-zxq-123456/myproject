package com.exampl.zxq.dubbo.extension.factory;


import com.exampl.zxq.dubbo.extension.ExtensionLoader;
import com.exampl.zxq.dubbo.extension.annotation.SPI;

public class SpiExtensionFactory implements ExtensionFactory {

    @Override
    public <T> T getExtension(Class<T> type, String name) {

        if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
            ExtensionLoader<T> loader = ExtensionLoader.getExtensionLoader(type);
            if (!loader.getSupportedExtensions().isEmpty()) {
                return loader.getAdaptiveExtension();
            }
        }
        return null;
    }
}
