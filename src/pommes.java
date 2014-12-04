import java.util.Arrays;

public class pommes {

    public static void main(String[] args) {
        // (filiale)
        byte[] var2 = new byte[]{(byte)77, (byte)97, (byte)103, (byte)105, (byte)99, (byte)32, (byte)107, (byte)101, (byte)121, (byte)32, (byte)105, (byte)115, (byte)32};
        byte[] var3 = new byte[]{(byte)73, (byte)32, (byte)99, (byte)97, (byte)110, (byte)32, (byte)115, (byte)101, (byte)101, (byte)32, (byte)116, (byte)104, (byte)101, (byte)32, (byte)112, (byte)97, (byte)99, (byte)107, (byte)97, (byte)103, (byte)101, (byte)32};
        byte[] var4 = new byte[]{(byte)89, (byte)111, (byte)117, (byte)32, (byte)109, (byte)117, (byte)115, (byte)116, (byte)32, (byte)109, (byte)111, (byte)118, (byte)101, (byte)32, (byte)116, (byte)104, (byte)97, (byte)116, (byte)32, (byte)112, (byte)97, (byte)99, (byte)107, (byte)97, (byte)103, (byte)101, (byte)32, (byte)111, (byte)117, (byte)116, (byte)32, (byte)111, (byte)102, (byte)32, (byte)109, (byte)121, (byte)32, (byte)115, (byte)105, (byte)103, (byte)104, (byte)116, (byte)33, (byte)33, (byte)33};
        byte[] var1 = new byte[]{(byte)79, (byte)111, (byte)112, (byte)115, (byte)44, (byte)32, (byte)115, (byte)111, (byte)109, (byte)101, (byte)116, (byte)104, (byte)105, (byte)110, (byte)103, (byte)32, (byte)102, (byte)117, (byte)110, (byte)100, (byte)97, (byte)109, (byte)101, (byte)110, (byte)116, (byte)97, (byte)108, (byte)32, (byte)104, (byte)97, (byte)115, (byte)32, (byte)103, (byte)111, (byte)110, (byte)101, (byte)32, (byte)119, (byte)114, (byte)111, (byte)110, (byte)103, (byte)32, (byte)45, (byte)32, (byte)115, (byte)101, (byte)101, (byte)32, (byte)115, (byte)116, (byte)97, (byte)99, (byte)107, (byte)32, (byte)116, (byte)114, (byte)97, (byte)99, (byte)101, (byte)33};
        byte[] var5 = new byte[]{(byte)40, (byte)72, (byte)97, (byte)118, (byte)101, (byte)32, (byte)121, (byte)111, (byte)117, (byte)32, (byte)108, (byte)97, (byte)117, (byte)110, (byte)99, (byte)104, (byte)101, (byte)100, (byte)32, (byte)121, (byte)111, (byte)117, (byte)114, (byte)32, (byte)112, (byte)114, (byte)111, (byte)103, (byte)114, (byte)97, (byte)109, (byte)109, (byte)101, (byte)115, (byte)32, (byte)105, (byte)110, (byte)32, (byte)116, (byte)104, (byte)101, (byte)32, (byte)99, (byte)111, (byte)114, (byte)114, (byte)101, (byte)99, (byte)116, (byte)32, (byte)111, (byte)114, (byte)100, (byte)101, (byte)114, (byte)63, (byte)41};
        String var33 = new String(var1) + "\n" + new String(var5);
        byte[] var6 = new byte[]{(byte)110, (byte)97, (byte)115, (byte)116, (byte)121, (byte)116, (byte)104, (byte)114, (byte)111, (byte)119, (byte)101, (byte)114, (byte)49};
        byte[] var7 = new byte[]{(byte)101, (byte)120, (byte)99, (byte)101, (byte)112, (byte)116, (byte)105, (byte)111, (byte)110, (byte)32, (byte)119, (byte)97, (byte)115, (byte)32, (byte)101, (byte)120, (byte)112, (byte)101, (byte)99, (byte)116, (byte)101, (byte)100};
        byte[] var8 = new byte[]{(byte)101, (byte)120, (byte)99, (byte)101, (byte)112, (byte)116, (byte)105, (byte)111, (byte)110, (byte)32, (byte)79, (byte)75};

        System.out.println("var2: " + new String(var2));
        System.out.println("var3: " + new String(var3));
        System.out.println("var4: " + new String(var4));
        System.out.println("var1: " + new String(var1));
        System.out.println("var5: " + new String(var5));
        System.out.println("var33: " + var33);
        System.out.println("var6: " + new String(var6));
        System.out.println("var7: " + new String(var7));
        System.out.println("var8: " + new String(var8));

        byte[] a = new byte[]{(byte)42, (byte)42, (byte)42};
        byte[] var10000 = new byte[]{(byte)73, (byte)110, (byte)97, (byte)112, (byte)112, (byte)114, (byte)111, (byte)112, (byte)114, (byte)105, (byte)97, (byte)116, (byte)101, (byte)32, (byte)73, (byte)110, (byte)118, (byte)97, (byte)108, (byte)105, (byte)100, (byte)80, (byte)97, (byte)114, (byte)97, (byte)109, (byte)69, (byte)120, (byte)99, (byte)101, (byte)112, (byte)116, (byte)105, (byte)111, (byte)110, (byte)32, (byte)70, (byte)65, (byte)73, (byte)76, (byte)69, (byte)68};
        byte[] b = new byte[]{(byte) 65, (byte) 99, (byte) 99, (byte) 111, (byte) 117, (byte) 110, (byte) 116, (byte) 32, (byte) 99, (byte) 114, (byte) 101, (byte) 97, (byte) 116, (byte) 101, (byte) 100};
        byte[] c = new byte[]{(byte) 85, (byte) 110, (byte) 101, (byte) 120, (byte) 112, (byte) 101, (byte) 99, (byte) 116, (byte) 101, (byte) 100, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110, (byte) 32, (byte) 101, (byte) 110, (byte) 99, (byte) 111, (byte) 117, (byte) 110, (byte) 116, (byte) 101, (byte) 114, (byte) 101, (byte) 100, (byte) 32, (byte) 111, (byte) 110, (byte) 32, (byte) 116, (byte) 114, (byte) 97, (byte) 110, (byte) 115, (byte) 102, (byte) 101, (byte) 114};
        byte[] d = new byte[]{(byte) 116, (byte) 114, (byte) 97, (byte) 110, (byte) 115, (byte) 102, (byte) 101, (byte) 114, (byte) 32, (byte) 79, (byte) 46, (byte) 75, (byte) 46};
        byte[] e = new byte[]{(byte) 105, (byte) 110, (byte) 97, (byte) 112, (byte) 112, (byte) 114, (byte) 111, (byte) 112, (byte) 114, (byte) 105, (byte) 97, (byte) 116, (byte) 101, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110};
        String f = new String(e);
        byte[] g = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 110, (byte) 97, (byte) 114, (byte) 114, (byte) 111, (byte) 119, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 32, (byte) 79, (byte) 46, (byte) 75, (byte) 46};
        byte[] h = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 110, (byte) 97, (byte) 114, (byte) 114, (byte) 111, (byte) 119, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 58, (byte) 32, (byte) 110, (byte) 101, (byte) 105, (byte) 116, (byte) 104, (byte) 101, (byte) 114, (byte) 32, (byte) 110, (byte) 117, (byte) 108, (byte) 108, (byte) 32, (byte) 110, (byte) 111, (byte) 114, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110, (byte) 33};
        byte[] i = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 110, (byte) 97, (byte) 114, (byte) 114, (byte) 111, (byte) 119, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 58, (byte) 32, (byte) 105, (byte) 110, (byte) 97, (byte) 112, (byte) 112, (byte) 114, (byte) 111, (byte) 112, (byte) 114, (byte) 105, (byte) 97, (byte) 116, (byte) 101, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110};
        String j = new String(a);
        String k = new String(b);
        String l = new String(c);
        String m = new String(d);
        byte[] n = new byte[]{(byte) 84, (byte) 104, (byte) 101, (byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 78, (byte) 111, (byte) 49};
        String o = new String(n);
        byte[] p = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 114, (byte) 101, (byte) 102, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 32, (byte) 102, (byte) 97, (byte) 105, (byte) 108, (byte) 101, (byte) 100, (byte) 32, (byte) 40, (byte) 110, (byte) 101, (byte) 105, (byte) 116, (byte) 104, (byte) 101, (byte) 114, (byte) 32, (byte) 110, (byte) 117, (byte) 108, (byte) 108, (byte) 32, (byte) 110, (byte) 111, (byte) 114, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110, (byte) 41, (byte) 46};
        String q = new String(p);
        byte[] r = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 114, (byte) 101, (byte) 102, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 32, (byte) 79, (byte) 46, (byte) 75, (byte) 46};
        String s = new String(r);
        byte[] t = new byte[]{(byte) 72, (byte) 111, (byte) 97, (byte) 120, (byte) 32, (byte) 114, (byte) 101, (byte) 102, (byte) 32, (byte) 116, (byte) 101, (byte) 115, (byte) 116, (byte) 58, (byte) 32, (byte) 105, (byte) 110, (byte) 97, (byte) 112, (byte) 112, (byte) 114, (byte) 111, (byte) 112, (byte) 114, (byte) 105, (byte) 97, (byte) 116, (byte) 101, (byte) 32, (byte) 101, (byte) 120, (byte) 99, (byte) 101, (byte) 112, (byte) 116, (byte) 105, (byte) 111, (byte) 110};
        String u = new String(t);
        String v = new String(g);
        String w = new String(h);
        String x = new String(i);
        int asdf = 1 + 1;
    }
}
