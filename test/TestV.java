
import java.util.Random;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.HtmlEmail;
 
/**
 * @author Agung Setiawan
 */
public class TestV {
 
    public static void main(String[] args) throws EmailException {
        String code = "";
        String tempA = "";
        StringBuilder sb = new StringBuilder();
        String kepo = "Hehe";
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            int a = rand.nextInt(9);

            tempA = String.valueOf(a);
            sb = sb.append(tempA);
            System.out.println(sb);
        }
        code = String.valueOf(sb);

        System.out.println(code);
    }
}
 
