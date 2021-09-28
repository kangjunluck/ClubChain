import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class testCode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String testText = "cf0f0a64538a8a005532d195605d334f02d6a89fde64f068f5a2d239531986e7";
        byte[] testToBytes = testText.getBytes("UTF-8");

        //Hex 인코딩(Byte To String)
        String encodeHexString = DatatypeConverter.printHexBinary(testToBytes);

        //Hex 디코딩(String to Byte)
        byte[] decodeHexByte = DatatypeConverter.parseHexBinary(encodeHexString);

        System.out.println("인코딩 전: " + testText);
        System.out.println("인코딩: " + encodeHexString);
        System.out.println("디코딩: " + new String(decodeHexByte));
    }
}
