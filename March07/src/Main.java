import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Ad: ");
      String name = scanner.nextLine();
      if (containsDigits(name)) {
        throw new TextContainsDigitsException("Ad alanı rakam içeremez.", name);
      }

      System.out.print("Soyad: ");
      String surname = scanner.nextLine();
      if (containsDigits(surname)) {
        throw new TextContainsDigitsException("Soyad alanı rakam içeremez.", surname);
      }

      System.out.print("TCKN: ");
      String tckn = scanner.nextLine();
      if (!isValidTCKN(tckn)) {
        throw new TCKNInvalidException("TCKN geçersiz. 11 karakter olmalı ve harf içeremez.");
      }

      System.out.print("Doğum Yılı: ");
      String birthYear = scanner.nextLine();
      if (!isValidBirthYear(birthYear)) {
        throw new BirthYearInvalidException("Doğum yılı geçersiz. 4 karakter olmalı ve harf içeremez.");
      }

      System.out.print("Email: ");
      String email = scanner.nextLine();
      if (!isValidEmail(email)) {
        throw new EmailInvalidException(
            "Email geçersiz. En az bir tane '@' karakteri içermeli ve .com ile bitmelidir.");
      }

      // Veriler doğru girilmiş, işleme devam edilebilir.
      System.out.println("Girilen bilgiler doğru.");
    } catch (TextContainsDigitsException e) {
      System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
    } catch (TCKNInvalidException | BirthYearInvalidException | EmailInvalidException e) {
      System.out.println(e.getMessage());
    }
  }

  private static boolean containsDigits(String str) {
    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isValidTCKN(String tckn) {
    if (tckn.length() != 11) {
      return false;
    }

    for (char c : tckn.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  private static boolean isValidBirthYear(String year) {
    if (year.length() != 4) {
      return false;
    }

    for (char c : year.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  private static boolean isValidEmail(String email) {
    return email.contains("@") && email.endsWith(".com");
  }
}