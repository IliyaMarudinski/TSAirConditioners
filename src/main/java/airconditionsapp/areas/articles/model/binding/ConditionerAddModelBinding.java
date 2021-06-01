package airconditionsapp.areas.articles.model.binding;

import java.util.List;

public class ConditionerAddModelBinding {
    private int id;
    private String name;
    private String img;
    private String description;
    private String brands;
    private int roomVolume;
    private double warrenty;
    private String power;
    private String energyClass;
    private String outSize;
    private String inSize;
    private double price;
    private double promoPrice;
    private List<String> images;

    public double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public int getRoomVolume() {
        return roomVolume;
    }

    public void setRoomVolume(int roomVolume) {
        this.roomVolume = roomVolume;
    }

    public double getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(double warrenty) {
        this.warrenty = warrenty;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getOutSize() {
        return outSize;
    }

    public void setOutSize(String outSize) {
        this.outSize = outSize;
    }

    public String getInSize() {
        return inSize;
    }

    public void setInSize(String inSize) {
        this.inSize = inSize;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

//    public List<String> splitImgs(){
//        String str = this.getImg();
//        String[] arrOfStr = str.split(",", 10);
//
//        for (String a : arrOfStr)
//            images.add(a);
//
//        System.out.println("CHECK--- size" + images.size());
//    }
}
