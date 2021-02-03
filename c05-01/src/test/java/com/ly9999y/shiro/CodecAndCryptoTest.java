package com.ly9999y.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

public class CodecAndCryptoTest {

    /**
     * base64
     */
    @Test
    public void testBase64() {
        String str = "hello";
        byte[] encode = Base64.encode(str.getBytes());
        System.out.println(new String(encode));
        String decode = Base64.decodeToString(encode);
        Assert.assertEquals(str, decode);
    }

    @Test
    public void testHex() {
        String str = "hello";
        String encode = Hex.encodeToString(str.getBytes());
        System.out.println(encode);
        String s = new String(Hex.decode(encode));
        Assert.assertEquals(str, s);
    }

    @Test
    public void testCodecSupport() {
        String str = "hello";
        byte[] bytes = CodecSupport.toBytes(str, "utf-8");
        String s = CodecSupport.toString(bytes);
        Assert.assertEquals(s, str);
    }

    @Test
    public void testRandom() {
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        secureRandomNumberGenerator.setSeed("123".getBytes());
        for (int i = 0; i < 10; i++) {
            System.out.println(secureRandomNumberGenerator.nextBytes().toHex());
        }
    }

    @Test
    public void testMd5() {
        String str = "hello";
        String salt = "123";
        String s = new Md5Hash(str, salt).toString();
        System.out.println(s);
    }

    @Test
    public void testSha1() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str, salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSha384() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha384Hash(str, salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSha512() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSimpleHash() {
        String str = "hello";
        String salt = "123";
        //MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);
    }

    @Test
    public void testHashService(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123"))
                .setIterations(2)
                .build();

        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    @Test
    public void testAesCipherService(){
        AesCipherService aesCipherService = new AesCipherService();

        aesCipherService.setKeySize(128);

        Key key = aesCipherService.generateNewKey();

        String text = "hello";

        String hex = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();

        String s = new String(aesCipherService.decrypt(Hex.decode(hex.getBytes()), key.getEncoded()).getBytes());

        Assert.assertEquals(s, text);

    }

    @Test
    public void testBlowfishCipherService() {
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setKeySize(128);

        //生成key
        Key key = blowfishCipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = blowfishCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(blowfishCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        junit.framework.Assert.assertEquals(text, text2);
    }

    @Test
    public void testDefaultBlockCipherService() throws Exception {


        //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);

        //生成key
        Key key = cipherService.generateNewKey();

        String text = "hello";

        //加密
        String encrptText = cipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        //解密
        String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        junit.framework.Assert.assertEquals(text, text2);
    }


    //加密/解密相关知识可参考snowolf的博客 http://snowolf.iteye.com/category/68576
}
