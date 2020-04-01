package com.exampl.zxq.dubbo.compiler;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JDKProxyCompiler extends AbstractCompiler {


    protected Class<?> doCompile(String name, String source) throws Throwable {
        return null;
    }

    private final class ClassLoaderImpl extends ClassLoader{

        private final Map<String, JavaFileObject> map = new HashMap<>();

        public ClassLoaderImpl(ClassLoader parent) {
            super(parent);
        }

        Collection<JavaFileObject> files(){
            return Collections.unmodifiableCollection(map.values());
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            JavaFileObject file = map.get(name);
            if (file != null){
                byte[] bytes = ((JavaFileObjectImpl) file).getByteCode();
                return defineClass(name,bytes,0,bytes.length);
            }
            return this.getClass().getClassLoader().loadClass(name);
        }

        @Override
        public InputStream getResourceAsStream(String name) {
            if (name.endsWith(".class")) {
                String qualifiedClassName = name.substring(0, name.length() - ".class".length()).replace('/', '.');
                JavaFileObjectImpl file = (JavaFileObjectImpl) map.get(qualifiedClassName);
                if (file != null) {
                    return new ByteArrayInputStream(file.getByteCode());
                }
            }
            return super.getResourceAsStream(name);
        }
    }


    private final class JavaFileObjectImpl extends SimpleJavaFileObject{
        private final CharSequence charSequence;
        private ByteArrayOutputStream byteCode;
        public JavaFileObjectImpl(URI uri, Kind kind) {
            super(uri, kind);
            charSequence = null;
        }
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return charSequence;
        }
        @Override
        public OutputStream openOutputStream() throws IOException {
            return byteCode = new ByteArrayOutputStream();
        }
        @Override
        public Kind getKind() {
            return super.getKind();
        }

        @Override
        public InputStream openInputStream() throws IOException {
            return new ByteArrayInputStream(byteCode.toByteArray());
        }
        public byte[] getByteCode(){
            return byteCode.toByteArray();
        }
    }
}


