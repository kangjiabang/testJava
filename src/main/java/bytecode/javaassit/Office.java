package javaassit;

public class Office {

    public final <A extends Address>   Address getAdress(String city,A  adress) {
        return new OfficeAddress("wuchangdadao");
    }

    public <A extends Address>  Address getAdress(String city,A  adress,int years,boolean isDeprecated) {
        return new OfficeAddress("wuchangdadao");
    }

}
