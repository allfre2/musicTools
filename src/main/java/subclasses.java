import com.allfre2.musicruler.*;
import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

public class subclasses{

 public static void main(String[] args) {
  //Package[] pcks = Package.getPackages();
  //Arrays.stream(pcks).forEach(System.out::println);
  String d = System.getProperty("user.dir");
  System.out.println(d);
  Package p = Chord.class.getPackage();
  System.out.println(p);
  // System.out.println(Chord.class.getDeclaredClasses());
  // Arrays.asList(Chord.class.getDeclaredMethods())
  //  .stream()
  //  .forEach(System.out::println);
  Arrays.asList(Chord.class.getInterfaces())
   .stream()
   .forEach(System.out::println);
 }
}
