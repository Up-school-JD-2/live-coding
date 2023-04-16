import java.util.EnumSet;

public enum Occupation {
  DIRECTOR,
  ACTOR,
  DOCTOR,
  LAWYER,
  DEVELOPER,
  TEACHER,
  WRITER,
  UNEMPLOYED;

  public static Occupation getOccupation(String occupation) {
    EnumSet<Occupation> occupations = EnumSet.allOf(Occupation.class);
    for (Occupation o : occupations) {
      if (o.name().equalsIgnoreCase(occupation)) {
        return o;
      }
    }
    return Occupation.UNEMPLOYED;
  }

  public static void listOccupations() {
    System.out.println("------ Meslekler -------");
    EnumSet<Occupation> occupations = EnumSet.allOf(Occupation.class);
    for (Occupation o : occupations) {
      System.out.println(o.name());
    }
    System.out.println("---------------------");
  }
}
