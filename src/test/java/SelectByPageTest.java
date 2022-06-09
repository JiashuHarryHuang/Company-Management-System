import com.pojo.PageBean;
import com.service.BrandService;

public class SelectByPageTest {
    public static void main(String[] args) {
        BrandService service = new BrandService();
        int begin = 0;
        int size = 5;
        System.out.println(service.selectByPage(begin, size));
    }
}
