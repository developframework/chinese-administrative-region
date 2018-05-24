package test;

import com.github.developframework.regiondata.China;
import com.github.developframework.regiondata.ChinaRegionProvider;
import com.github.developframework.regiondata.Province;
import com.github.developframework.regiondata.output.ConsoleOutput;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * @author qiuzhenhao
 */
public class RegionTest {

    private ChinaRegionProvider chinaRegionProvider;

    @Before
    public void setUp() {
        chinaRegionProvider = new ChinaRegionProvider();
    }

    @Test
    public void testRegion() {
        China china = chinaRegionProvider.getChina();
        Optional<Province> provinceOptional = china.getProvinceByName("浙江省");
        provinceOptional.ifPresent(ConsoleOutput::print);
    }
}
