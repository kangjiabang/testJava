import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import validation.Student;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/**
 * @Author：zeqi
 * @Date: Created in 10:45 27/3/18.
 * @Description:
 */
public class BasicTest {


    @Test
    public void testInvoke() {
        this.invoke("name");
    }

    /*public void invoke(String name,String... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }*/

    public void invoke(String name, Object... value) {
        if (value == null) {
            return;
        }
        System.out.println(name);
    }

    @Test
    public void testStringFormat() {
        System.out.println(String.format("计算金额异常，aid=%d", 231));
        System.out.println(String.format("计算金额异常，aid=%s", 231));
    }

    @Test
    public void testStringThreeTuple() {
        String result = "Hello" + ("Hello" == null ? "world" : " 你好");
        System.out.println(result);
    }

    @Test
    public void testVersionCompare() {
        System.out.println("7.0".compareTo("5.9.6"));
    }

    @Test
    public void random4Digit() {
        System.out.println((int) ((Math.random() * 9 + 1) * 1000));

        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random().nextInt(10));
        System.out.println(new Random(10).nextInt());
        System.out.println(new Random(10).nextInt());
    }

    @Test
    public void testConcurrentMap() {
        ConcurrentMap<String, List<Integer>> queueIdMap = Maps.newConcurrentMap();


        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            queueIdMap.put("String", null);
        });
    }


    @Test
    public void testHttpClient() {

    }

    @Test
    public void testExceptionCatch() {
        try {
            testThrowException();
        } catch (Exception e) {
            String result = ExceptionUtils.getStackTrace(e);
            Throwable t = new Throwable(result);
            RuntimeException runtimeException = new RuntimeException(t);
            t.getCause();
        }
    }

    @Test
    public void testFastJson() {
        try {
            Student stuent = JSONObject.parseObject("{\"sex\":\"BOY\"}", Student.class);

            Student.SEX sex = JSON.parseObject("\"BOY\"", Student.SEX.class);
            System.out.println("student: " + stuent);
            System.out.println("sex: " + sex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFastJsonParse() {
        try {

            String value = "{\"carSoMstrDTO\":{\"groupCode\":\"e00001036nm\",\"shopCode\":\"102020\",\"shopName\":\"广州鸿粤雷克萨斯汽车销售服务有限公司\",\"deptId\":\"\",\"deptName\":\"\",\"orderCode\":\"E\n" +
                    "17439110364\",\"orderTypeCode\":null,\"orderTypeName\":null,\"soTypeCode\":\"0\",\"soTypeName\":\"新车销售订单\",\"actOwnerNo\":\"\",\"actOwnerName\":\"陈奕文\",\"actOwnerMobile\":\"13826047297\n" +
                    "\",\"actOwnerCardNo\":\"441322198405230415\",\"cusNo\":\"rtABinHrz8\",\"cusName\":\"陈奕文\",\"cusCardNo\":\"441322198405230415\",\"cusMobile\":\"13922139622\",\"soStatusCode\":\"0\",\"soStatusNa\n" +
                    "me\":\"草稿\",\"depositAmount\":20000,\"soTotalAmount\":418144,\"decorateTotalAmount\":4965,\"ewTotalAmount\":1579,\"finTotalAmount\":0,\"insTotalAmount\":0,\"otherServiceTotalAmount\":3\n" +
                    "7600,\"expectedDeliveryDate\":\"2019-12-31T00:00:00\",\"soApprovedDate\":\"\",\"soDate\":\"2019-11-30T21:19:22\",\"saEmpId\":\"116081\",\"saEmpName\":\"刘少珍\",\"userId\":\"116081\",\"userName\"\n" +
                    ":\"刘少珍\",\"isSnapshot\":null,\"oldOrderCode\":null,\"versionNumber\":6,\"carIDRegistration\":\"公司代办\",\"carAdditionTotalAmount\":0,\"carReplacementAmount\":null,\"invoiceCompanyNo\n" +
                    "\":\"102020\",\"invoiceCompanyName\":\"广州鸿粤雷克萨斯汽车销售服务有限公司\",\"approvalType\":\"开票申请\",\"orderSalesChannel\":\"展厅\",\"payMethod\":\"1\",\"payType\":1,\"remark\":\"NX300h\n" +
                    "锋尚版，指导价384000 销售价374000成交；购买4年/10万公里自主延保9480元；我司代办广州牌，一次性，购置税33100实报实销，购买精品5180元含：原厂膜、记录仪、发动机护板、五件套\n" +
                    "；赠送精品：无；2019年12月份拿车。保险14023自付保险公司、1579自主玻璃\",\"carAdditionType\":\"\",\"store2SNo\":\"\",\"store2SName\":\"\"},\"carSoDetDTO\":{\"vin\":\"\",\"carBrandCode\":\"bran\n" +
                    "d-92\",\"carBrandName\":\"雷克萨斯\",\"carSeriesCode\":\"series-1944\",\"carSeriesName\":\"雷克萨斯NX\",\"carCategoryCode\":null,\"carCategoryName\":null,\"carModelCode\":\"CXXF1021018\",\"ca\n" +
                    "rModelName\":\"2020款 雷克萨斯NX 300h 锋尚版\",\"carColorCode\":\"RHYS00010\",\"carColorName\":\"超音速钛银\",\"carInnerColorCode\":\"RHNS00008\",\"carInnerColorName\":\"黑色\",\"carGuideAm\n" +
                    "ount\":384000,\"salesQuotationAmount\":374000,\"outerModelCode\":null},\"carInsMstrDTO\":{\"insBranchNo\":\"insBranch_1812006545\",\"insBranchName\":\"中国人民财产保险股份有限公司广州\n" +
                    "市分公司\",\"statusName\":null,\"totalQty\":7,\"compulsoryInsurances\":[{\"insNo\":\"1487\",\"insName\":\"座位险-驾驶员（主险）\",\"insTypeCode\":null,\"insTypeName\":\"机动车辆商业保险\",\"p\n" +
                    "ayTypeCode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":0,\"insDetCommissionRate\":null},{\"insNo\":\"1485\",\"insName\":\"盗抢险（主险）\",\"insTypeCode\n" +
                    "\":null,\"insTypeName\":\"机动车辆商业保险\",\"payTypeCode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":0,\"insDetCommissionRate\":null},{\"insNo\":\"148\n" +
                    "3\",\"insName\":\"车损险（主险）\",\"insTypeCode\":null,\"insTypeName\":\"机动车辆商业保险\",\"payTypeCode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":13\n" +
                    "018,\"insDetCommissionRate\":null},{\"insNo\":\"1482\",\"insName\":\"交强险\",\"insTypeCode\":null,\"insTypeName\":\"机动车交通事故责任强制保险\",\"payTypeCode\":null,\"payTypeName\":null,\"\n" +
                    "insCoverageAmount\":0,\"estimatedPremiumAmount\":950,\"insDetCommissionRate\":null},{\"insNo\":\"1481\",\"insName\":\"车船使用税\",\"insTypeCode\":null,\"insTypeName\":\"车船税\",\"payTypeC\n" +
                    "ode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":55,\"insDetCommissionRate\":null},{\"insNo\":\"1484\",\"insName\":\"三者险（主险）\",\"insTypeCode\":null\n" +
                    ",\"insTypeName\":\"机动车辆商业保险\",\"payTypeCode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":0,\"insDetCommissionRate\":null},{\"insNo\":\"1486\",\"in\n" +
                    "sName\":\"座位险-乘客（主险）\",\"insTypeCode\":null,\"insTypeName\":\"机动车辆商业保险\",\"payTypeCode\":null,\"payTypeName\":null,\"insCoverageAmount\":0,\"estimatedPremiumAmount\":0,\"\n" +
                    "insDetCommissionRate\":null}]},\"carFinSoDetDTO\":null,\"carEwDetDTO\":{\"ewProductNo\":\"191128000001\",\"ewProductName\":\"自主玻璃新保\",\"ewBizCategoryCode\":null,\"ewBizCategoryNam\n" +
                    "e\":\"\",\"salesMethod\":\"\",\"ewProductSupplierNo\":null,\"ewProductSupplierName\":\"自主玻璃服务\",\"timeMileageId\":null,\"timeMileageName\":\"12月9999999公里\",\"ewTypeCode\":null,\"ewTy\n" +
                    "peName\":\"自主玻璃新保\",\"ewRetailPrice\":200,\"ewAmount\":1579,\"ewStartDate\":\"0001-01-01T00:00:00\",\"ewEndDate\":\"0001-01-01T00:00:00\",\"payTypeCode\":null,\"payTypeName\":null,\"s\n" +
                    "tatusName\":null,\"totalQty\":\"1\"},\"carOtherServiceSoDTOs\":[{\"serviceNo\":\"CLGZS0001\",\"serviceName\":\"车辆购置税\",\"serviceTypeCode\":null,\"serviceTypeName\":null,\"receiveAmount\n" +
                    "\":33100,\"actAmount\":0,\"statusName\":null,\"qty\":1},{\"serviceNo\":\"DS1AMV0000001\",\"serviceName\":\"上牌费\",\"serviceTypeCode\":null,\"serviceTypeName\":null,\"receiveAmount\":4500,\"\n" +
                    "actAmount\":0,\"statusName\":null,\"qty\":1}],\"decorationDetDTOs\":[{\"goodsNo\":\"JZ-JK-GDTZ273\",\"goodsName\":\"15款NX200 2.0L发动机下护板（塑钢）\",\"payTypeCode\":\"2\",\"payTypeName\"\n" +
                    ":\"店内付费\",\"price\":400,\"qty\":1,\"amount\":400,\"receiveAmount\":615,\"goodsQualityNo\":null,\"goodsQualityName\":\"非厂商\",\"goodsL1CategoryCode\":\"JP\",\"goodsL1CategoryName\":\"精品\n" +
                    "\",\"statusName\":null,\"discountRate\":1.5375,\"bizType\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YC-PZD2400300\",\"goodsName\":\"雷克萨斯纯正纳米隔热膜(高清无阻系列\n" +
                    "）\",\"payTypeCode\":\"0\",\"payTypeName\":\"客户付费\",\"price\":3000,\"qty\":1,\"amount\":3000,\"receiveAmount\":3000,\"goodsQualityNo\":null,\"goodsQualityName\":\"厂商\",\"goodsL1CategoryCo\n" +
                    "de\":\"JP\",\"goodsL1CategoryName\":\"精品\",\"statusName\":null,\"discountRate\":1,\"bizType\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YC-PZD6176300\",\"goodsName\":\"WiFi\n" +
                    "行车记录仪(带屏款同造型)\",\"payTypeCode\":\"0\",\"payTypeName\":\"客户付费\",\"price\":1800,\"qty\":1,\"amount\":1800,\"receiveAmount\":1800,\"goodsQualityNo\":null,\"goodsQualityName\":\"厂\n" +
                    "商\",\"goodsL1CategoryCode\":\"JP\",\"goodsL1CategoryName\":\"精品\",\"statusName\":null,\"discountRate\":1,\"bizType\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YZ-HC-5-110\n" +
                    "1\",\"goodsName\":\"韩国绒磨毛抱枕5件套 HC-1101\",\"payTypeCode\":\"0\",\"payTypeName\":\"客户付费\",\"price\":165,\"qty\":1,\"amount\":165,\"receiveAmount\":165,\"goodsQualityNo\":null,\"goods\n" +
                    "QualityName\":\"非厂商\",\"goodsL1CategoryCode\":\"JP\",\"goodsL1CategoryName\":\"精品\",\"statusName\":null,\"discountRate\":1,\"bizType\":\"JP\",\"incrementDecorateSoDetDtoList\":[]}]}";
           // OrderDTO orderDTO = JSON.parseObject(value, OrderDTO.class);

            //OrderDTO orderDTO = SouCheObjectUtil.convertValue(value,OrderDTO.class);
        //   System.out.println(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFastJsonParse3() {
        try {

            String value = "{\"carEwDetDTO\":{\"ewAmount\":700.0,\"ewProductName\":\"平安厂商延保发动机、变速箱12月3万公里\",\"ewProductNo\":\"YBCP0076\",\"ewProductSupplierName\":\"中国人民财产保险股份有限公司福建分公司\",\"ewRetailPrice\":0.0,\"ewTypeName\":\"发动机变速箱\",\"salesMethod\":\"销售\",\"timeMileageName\":\"12月30000公里\",\"totalQty\":1.0},\"carFinSoDetDTO\":{\"downPaymentAmount\":26600,\"loanAmount\":98400,\"loanTermName\":\"36\",\"productName\":\"3年低利率\",\"productNo\":\"F100174\",\"productProviderName\":\"partner_一汽汽车金融有限>公司\",\"productTypeName\":\"厂商金融\",\"serviceChargeAmount\":3600},\"carInsMstrDTO\":{\"compulsoryInsurances\":[{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"商业险总额(预估)\",\"insNo\":\"581\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"发动机涉水损失险\",\"insNo\":\"430\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"全车盗抢保险\",\"insNo\":\"425\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"玻璃单独破碎险\",\"insNo\":\"426\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"自燃损失>险\",\"insNo\":\"427\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"第三者附加险法定节假日限额翻倍险\",\"insNo\":\"801\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"车损险无法找到第三方特约险\",\"insNo\":\"566\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"机动车交通事故责任强制保险\",\"insNo\":\"421\",\"insTypeName\":\"机动车交通事故责任强制保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"车船税\",\"insNo\":\"541\",\"insTypeName\":\"车船税\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"机动车损失保险\",\"insNo\":\"422\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"车上人员责任保险_司机\",\"insNo\":\"424\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"车上人员责任保险_乘客\",\"insNo\":\"551\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"不计免赔率险\",\"insNo\":\"434\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"第三者责任保险\",\"insNo\":\"423\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"车身划痕损失险\",\"insNo\":\"429\",\"insTypeName\":\"机动车辆商业保险\"}],\"insBranchName\":\"中国人民财产保险股份有限公司福建分公司\",\"insBranchNo\":\"insBranch_0102\",\"totalQty\":15},\"carOtherServiceSoDTOs\":[{\"receiveAmount\":1200,\"qty\":1,\"serviceNo\":\"DS1CMV0000002\",\"serviceName\":\"服务费1\",\"actAmount\":0},{\"receiveAmount\":1000,\"qty\":1,\"serviceNo\":\"DS1DMV0000001\",\"serviceName\":\"服务费4\",\"actAmount\":0},{\"receiveAmount\":4100,\"qty\":1,\"serviceNo\":\"DS2DMV0000001\",\"serviceName\":\"服务费2\",\"actAmount\":0},{\"receiveAmount\":580,\"qty\":1,\"serviceNo\":\"DS5CMV0000008\",\"serviceName\":\"盗抢自营\",\"actAmount\":0}],\"carSoDetDTO\":{\"carBrandCode\":\"Y001\",\"carBrandName\":\"一汽-大众\",\"carColorCode\":\"CSS6775\",\"carColorName\":\"极地白\",\"carGuideAmount\":150500,\"carInnerColorCode\":\"NSS6331\",\"carInnerColorName\":\"黑色内饰\",\"carModelCode\":\"Y0010856\",\"carModelName\":\"2020款 宝来 全新宝来 1.4T自动精英型 国六\",\"carSeriesCode\":\"Y00111\",\"carSeriesName\":\"宝来MQB\",\"salesQuotationAmount\":125000,\"vin\":\"\"},\"carSoMstrDTO\":{\"actOwnerCardNo\":\"350702199203298911\",\"actOwnerMobile\":\"17705995909\",\"actOwnerName\":\"王承锭\",\"actOwnerNo\":\"KlKYNTjas5\",\"approvalType\":\"开票申请\",\"carAdditionTotalAmount\":0,\"carAdditionType\":\"\",\"carIDRegistration\":\"本地上牌\",\"cusCardNo\":\"350702199203298911\",\"cusMobile\":\"17705995909\",\"cusName\":\"王承锭\",\"cusNo\":\"KlKYNTjas5\",\"decorateTotalAmount\":5090,\"depositAmount\":5000,\"deptName\":\"\",\"ewTotalAmount\":700,\"expectedDeliveryDate\":1575561600000,\"finTotalAmount\":98400,\"groupCode\":\"e00000877nm\",\"insTotalAmount\":0,\"invoiceCompanyName\":\"南平盈众\",\"invoiceCompanyNo\":\"10059\",\"isSrp\":0,\"orderCode\":\"E13133093634\",\"orderSalesChannel\":\"展厅自然进店\",\"otherServiceTotalAmount\":6880,\"payMethod\":\"0\",\"payType\":1,\"remark\":\"订车\",\"saEmpId\":\"111838\",\"saEmpName\":\"高文燕\",\"shopCode\":\"10059\",\"shopName\":\"南平盈众\",\"soDate\":1575336380000,\"soStatusCode\":\"1\",\"soStatusName\":\"审批中\",\"soTotalAmount\":137670,\"soTypeCode\":\"10\",\"soTypeName\":\"展厅订单\",\"store2SName\":\"\",\"store2SNo\":\"\",\"userId\":\"111838\",\"userName\":\"高文燕\",\"versionNumber\":1},\"decorationDetDTOs\":[{\"goodsNo\":\"JP2B13-XMTL-0001\",\"discountRate\":1,\"amount\":88,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":88,\"payTypeCode\":\"2\",\"price\":88,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"专车专用挡泥板（普通车型）\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2C11-XMSB-0024\",\"discountRate\":1,\"amount\":298,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":298,\"payTypeCode\":\"2\",\"price\":298,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"商邦车标四件套\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2C11-XMSB-0189\",\"discountRate\":1,\"amount\":298,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":298,\"payTypeCode\":\"2\",\"price\":298,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"爱特斯吉普钥匙包\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2C04-XMSB-0012\",\"discountRate\":1,\"amount\":980,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":980,\"payTypeCode\":\"2\",\"price\":980,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"立体全包围皮革二合一脚垫\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2A03-LYCF-0002\",\"discountRate\":1,\"amount\":580,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":580,\"payTypeCode\":\"2\",\"price\":580,\"goodsQualityName\":\"厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"车眼睛倒车摄像头-高清王打孔或车牌灯\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2A01-SZLY-0012\",\"discountRate\":1,\"amount\":5800,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":5800,\"payTypeCode\":\"2\",\"price\":5800,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"路畅6核T7主机（2G运存+32G存储+六核超强处理器）\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2A99-GZJHD-0001\",\"discountRate\":1,\"amount\":3500,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":3500,\"payTypeCode\":\"2\",\"price\":3500,\"goodsQualityName\":\"\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"金华达－泰尼泊恩大众电动座椅无损升级司机座-六向调节（不含安装）\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2A17-SZFC-0002\",\"discountRate\":1,\"amount\":1680,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":1680,\"payTypeCode\":\"2\",\"price\":1680,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"富创专车专用隐藏式V7行车记录仪系统\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"LFV858015\",\"discountRate\":0.78070175,\"amount\":4560,\"bizType\":\"JP\",\"payTypeName\":\"客户付费\",\"receiveAmount\":3560,\"payTypeCode\":\"0\",\"price\":2280,\"goodsQualityName\":\"厂商\",\"qty\":2,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"太阳膜(前档)自然色\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"JP2B01-NJYD-0022\",\"discountRate\":0.70183486,\"amount\":2180,\"bizType\":\"JP\",\"payTypeName\":\"客户付费\",\"receiveAmount\":1530,\"payTypeCode\":\"0\",\"price\":2180,\"goodsQualityName\":\"\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"联邦圣泰鳄鱼侧后档-一汽大众（20台/卷）\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]}]}";
            //OrderDTO orderDTO = JSON.parseObject(value, OrderDTO.class);

            //OrderDTO orderDTO = SouCheObjectUtil.convertValue(value,OrderDTO.class);
          //  System.out.println(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFastJsonParse2() {
        try {
            String value ="{\"carEwDetDTO\":{\"ewAmount\":1579.0,\"ewProductName\":\"自主玻璃新保\",\"ewProductNo\":\"191128000001\",\"ewProductSupplierName\":\"自主玻璃服务\",\"ewRetailPrice\":200.0,\"ewTypeName\":\"自主玻璃新保\",\"timeMileageName\":\"12月9999999公里\",\"totalQty\":1.0},\"carInsMstrDTO\":{\"compulsoryInsurances\":[{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"座位险-驾驶员（主险）\",\"insNo\":\"1487\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"盗抢险>（主险）\",\"insNo\":\"1485\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":13018,\"insCoverageAmount\":0,\"insName\":\"车损险（主险）\",\"insNo\":\"1483\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":950,\"insCoverageAmount\":0,\"insName\":\"交强险\",\"insNo\":\"1482\",\"insTypeName\":\"机动车交通事故责任强制保险\"},{\"estimatedPremiumAmount\":55,\"insCoverageAmount\":0,\"insName\":\"车船使用税\",\"insNo\":\"1481\",\"insTypeName\":\"车船税\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"三者险（主险）\",\"insNo\":\"1484\",\"insTypeName\":\"机动车辆商业保险\"},{\"estimatedPremiumAmount\":0,\"insCoverageAmount\":0,\"insName\":\"座位险-乘客（主险）\",\"insNo\":\"1486\",\"insTypeName\":\"机动车辆商业保险\"}],\"insBranchName\":\"中国人民财产保险股份有限公司广州市分公司\",\"insBranchNo\":\"insBranch_1812006545\",\"totalQty\":7},\"carOtherServiceSoDTOs\":[{\"receiveAmount\":33100,\"qty\":1,\"serviceNo\":\"CLGZS0001\",\"serviceName\":\"车辆购置税\",\"actAmount\":0},{\"receiveAmount\":4500,\"qty\":1,\"serviceNo\":\"DS1AMV0000001\",\"serviceName\":\"上牌费\",\"actAmount\":0}],\"carSoDetDTO\":{\"carBrandCode\":\"brand-92\",\"carBrandName\":\"雷克萨斯\",\"carColorCode\":\"RHYS00010\",\"carColorName\":\"超音速钛银\",\"carGuideAmount\":384000,\"carInnerColorCode\":\"RHNS00008\",\"carInnerColorName\":\"黑色\",\"carModelCode\":\"CXXF1021018\",\"carModelName\":\"2020款 雷克萨斯NX 300h 锋尚版\",\"carSeriesCode\":\"series-1944\",\"carSeriesName\":\"雷克>萨斯NX\",\"salesQuotationAmount\":374000,\"vin\":\"\"},\"carSoMstrDTO\":{\"actOwnerCardNo\":\"441322198405230415\",\"actOwnerMobile\":\"13826047297\",\"actOwnerName\":\"陈奕文\",\"actOwnerNo\":\"\",\"approvalType\":\"开票申请\",\"carAdditionTotalAmount\":0,\"carAdditionType\":\"\",\"carIDRegistration\":\"公司代办\",\"cusCardNo\":\"441322198405230415\",\"cusMobile\":\"13922139622\",\"cusName\":\"陈奕文\",\"cusNo\":\"rtABinHrz8\",\"decorateTotalAmount\":4965,\"depositAmount\":20000,\"deptName\":\"\",\"ewTotalAmount\":1579,\"expectedDeliveryDate\":1577721600000,\"finTotalAmount\":0,\"groupCode\":\"e00001036nm\",\"insTotalAmount\":0,\"invoiceCompanyName\":\"广州鸿粤雷克萨斯汽车销售服务有限公司\",\"invoiceCompanyNo\":\"102020\",\"isSrp\":0,\"orderCode\":\"E17439110364\",\"orderSalesChannel\":\"展厅\",\"otherServiceTotalAmount\":37600,\"payMethod\":\"1\",\"payType\":1,\"remark\":\"NX300h锋尚版，指导价384000 销售价374000成交；老客户魏子栋粤A295ZD介绍.购买4年/10万公里自主延保9480元；我司置换、代办广州牌，一次性，购置税33100实报实销，购买精品5180元含：原厂膜、记录仪、五件套；赠送精品：发动机护板；12月份拿车。>保险14023自付、1579自主玻璃\",\"saEmpId\":\"116081\",\"saEmpName\":\"刘少珍\",\"shopCode\":\"102020\",\"shopName\":\"广州鸿粤雷克萨斯汽车销售服务有限公司\",\"soDate\":1575119962000,\"soStatusCode\":\"0\",\"soStatusName\":\"草稿\",\"soTotalAmount\":418144,\"soTypeCode\":\"0\",\"soTypeName\":\"新车销售订单\",\"store2SName\":\"\",\"store2SNo\":\"\",\"userId\":\"116081\",\"userName\":\"刘少>珍\",\"versionNumber\":9},\"decorationDetDTOs\":[{\"goodsNo\":\"JZ-JK-GDTZ273\",\"discountRate\":1,\"amount\":400,\"bizType\":\"JP\",\"payTypeName\":\"店内付费\",\"receiveAmount\":400,\"payTypeCode\":\"2\",\"price\":400,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"15款NX200 2.0L发动机下护板（塑钢）\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YC-CX20190001\",\"discountRate\":1,\"amount\":3000,\"bizType\":\"JP\",\"payTypeName\":\"客户付费\",\"receiveAmount\":3000,\"payTypeCode\":\"0\",\"price\":3000,\"goodsQualityName\":\"厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"畅享高清套装\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YC-PZD6176300\",\"discountRate\":1,\"amount\":1800,\"bizType\":\"JP\",\"payTypeName\":\"客户付费\",\"receiveAmount\":1800,\"payTypeCode\":\"0\",\"price\":1800,\"goodsQualityName\":\"厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"WiFi 行车记录仪(带屏款同造型)\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]},{\"goodsNo\":\"YZ-HC-5-1101\",\"discountRate\":1,\"amount\":165,\"bizType\":\"JP\",\"payTypeName\":\"客户付费\",\"receiveAmount\":165,\"payTypeCode\":\"0\",\"price\":165,\"goodsQualityName\":\"非厂商\",\"qty\":1,\"goodsL1CategoryName\":\"精品\",\"goodsName\":\"韩国绒磨毛抱枕5件套 HC-1101\",\"goodsL1CategoryCode\":\"JP\",\"incrementDecorateSoDetDtoList\":[]}]}";
         //   OrderDTO orderDTO = JSON.parseObject(value, OrderDTO.class);

           // OrderDTO orderDTO = SouCheObjectUtil.convertValue(value,OrderDTO.class);
        //    System.out.println(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    return new NullPointerException("null");
                });

        completableFuture.completeExceptionally(new RuntimeException("has error"));

        completableFuture.get();

        Thread.sleep(3000);
    }


    public void testThrowException() {

        int a = 0;

        int result = a / 0;

    }

}
