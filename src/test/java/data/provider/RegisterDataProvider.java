package data.provider;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import commons.helpers.CommonHelper;
import data.helpers.RegisterDataHelper;
import dataObjects.RegisterTestData;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegisterDataProvider {

    @DataProvider(name = "Invalid Emails")
    public Iterator<Object[]> getRegisterData() {
        // Bước 1: Chỉ ra đường dẫn đến file Excel
        File file = new File("src/test/resources/RegisterData.xlsx");
        // Bước 2: Tạo tùy chọn khi đọc Excel (ở đây ta dùng mặc định)
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().caseInsensitive(false).ignoreWhitespaces(true).build();
        // Bước 3: Gọi hàm đọc Excel, ánh xạ từng dòng thành 1 object của RegisterData
        List<RegisterTestData> dataList = Poiji.fromExcel(file, RegisterTestData.class, options);
        //Bước 4: Chuyển data list --> thành dạng Object[][] để return trả về.
        List<Object[]> result = new ArrayList<Object[]>();
        for (RegisterTestData data : dataList) {
            Object[] row = new Object[]{data}; // Mỗi dòng là 1 Object[] có 1 phần tử
            result.add(row);
        }
        // Trả về Iterator<Object[]>
        return result.iterator();
    }

    @DataProvider(name="Invalid Password")
    public Object[][] provideInvalidPassword() {
        return new Object[][]{
                {RegisterTestData.builder()
                        .firstName(RegisterDataHelper.FIRST_NAME)
                        .lastName(RegisterDataHelper.LAST_NAME)
                        .emailAddress(RegisterDataHelper.getUniqueEmailAddress())
                        .companyName(RegisterDataHelper.COMPANY_NAME)
                        .password("1234")
                        .confirmPassword("1234")
                        .gender(RegisterDataHelper.GENDER).build()},
                {RegisterTestData.builder()
                        .firstName(RegisterDataHelper.FIRST_NAME)
                        .lastName(RegisterDataHelper.LAST_NAME)
                        .emailAddress(RegisterDataHelper.getUniqueEmailAddress())
                        .companyName(RegisterDataHelper.COMPANY_NAME)
                        .password(CommonHelper.generateRandomAlphanumeric(65))
                        .confirmPassword(CommonHelper.generateRandomAlphanumeric(65))
                        .gender(RegisterDataHelper.GENDER).build()},
        };
    }

    @DataProvider(name="Mismatch confirm password")
    public Object[][] provideMismatchPassword() {
        return new Object[][]{
                {RegisterTestData.builder()
                        .firstName(RegisterDataHelper.FIRST_NAME)
                        .lastName(RegisterDataHelper.LAST_NAME)
                        .emailAddress(RegisterDataHelper.getUniqueEmailAddress())
                        .companyName(RegisterDataHelper.COMPANY_NAME)
                        .password(RegisterDataHelper.PASSWORD)
                        .confirmPassword("1234567890987")
                        .gender(RegisterDataHelper.GENDER)}
        };
    }

}
