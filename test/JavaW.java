public class JavaW {

  public static void main(String arg[]) throws Exception {
      byte[] a = null;
      a=encrypt("String");
      String b = new String(a);
    System.out.println(b);
  }

  public static byte[] encrypt(String x) throws Exception {
    java.security.MessageDigest d = null;
    d = java.security.MessageDigest.getInstance("SHA-1");
    d.reset();
    d.update(x.getBytes());
    return d.digest();
  }
}