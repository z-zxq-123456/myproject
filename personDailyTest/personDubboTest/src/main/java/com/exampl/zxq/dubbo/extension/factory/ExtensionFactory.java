package com.exampl.zxq.dubbo.extension.factory;

import com.exampl.zxq.dubbo.extension.annotation.SPI;

/**
 * ExtensionFactory
 */
@SPI
public interface ExtensionFactory {

    /**
     * @describe: getExtension
     * @param:  type  name
     * @Time: 2019/7/9/009
     */
  <T> T getExtension(Class<T> type,String name);
}
