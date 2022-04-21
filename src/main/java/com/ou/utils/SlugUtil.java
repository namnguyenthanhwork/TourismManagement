package com.ou.utils;

//package com.ou.utils;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class SlugUtil {
//    private final static Pattern pattern1 = Pattern.compile("[áàảạãăắằẳẵặâấầẩẫậ]+");
//    private final static Pattern pattern2 = Pattern.compile("[éèẻẽẹêếềểễệ]+");
//    private final static Pattern pattern3 = Pattern.compile("[iíìỉĩị]+");
//    private final static Pattern pattern4 = Pattern.compile("[óòỏõọôốồổỗộơớờởỡợ]+");
//    private final static Pattern pattern5 = Pattern.compile("[úùủũụưứừửữự]+");
//    private final static Pattern pattern6 = Pattern.compile("[ýỳỷỹỵ]+");
//    private final static Pattern pattern7 = Pattern.compile("[đ]+");
//    private final static Pattern pattern8 = Pattern.compile("[`~!@#|$%^&*()+=,.\\/?><'\":;_]+");
//    private final static Pattern pattern9 = Pattern.compile("\\s+");
//    private final static Pattern pattern10 = Pattern.compile("[-]+");
//    private final static Pattern pattern11 = Pattern.compile("@-|-@|@");
//    private String slug;
//
//    public SlugUtil(String text){
//        this.slug=text;
//    }
//    public String getSlug() {
//        this.slug = this.slug.trim().toLowerCase();
//        Matcher matcher = pattern1.matcher(this.slug);
//        this.slug= matcher.replaceAll("a");
//        matcher = pattern2.matcher(this.slug);
//        this.slug= matcher.replaceAll("e");
//        matcher = pattern3.matcher(this.slug);
//        this.slug= matcher.replaceAll("i");
//        matcher = pattern4.matcher(this.slug);
//        this.slug= matcher.replaceAll("o");
//        matcher = pattern5.matcher(this.slug);
//        this.slug= matcher.replaceAll("u");
//        matcher = pattern6.matcher(this.slug);
//        this.slug= matcher.replaceAll("i");
//        matcher = pattern7.matcher(this.slug);
//        this.slug= matcher.replaceAll("d");
//        matcher = pattern8.matcher(this.slug);
//        this.slug= matcher.replaceAll("");
//        matcher = pattern9.matcher(this.slug);
//        this.slug= matcher.replaceAll("-");
//        matcher = pattern10.matcher(this.slug);
//        this.slug= matcher.replaceAll("-");
//        this.slug = String.format("@%s@", this.slug);
//        matcher = pattern11.matcher(this.slug);
//        this.slug= matcher.replaceAll("");
//        return slug;
//    }
//
//}
public class SlugUtil {
}