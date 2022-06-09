import com.pojo.Brand;
import com.service.BrandService;

import java.util.List;

public class DeleteByIdsTest {
    public static void main(String[] args) {
        BrandService service = new BrandService();
        int[] ids = {4, 5, 6};
        service.deleteByIds(ids);
        List<Brand> brands = service.selectAll();
        System.out.println(brands.get(0));
    }
}
