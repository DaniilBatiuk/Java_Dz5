package org.example;
import java.util.*;

public class Main {
    private static void incrementPopularity(Map<String, Integer> popularityCounter, String word) {
        popularityCounter.put(word, popularityCounter.getOrDefault(word, 0) + 1);
    }

    private static void decrementPopularity(Map<String, Integer> popularityCounter, String word) {
        int popularity = popularityCounter.getOrDefault(word, 0);
        if (popularity > 0) {
            popularityCounter.put(word, popularity - 1);
        }
    }

    private static void displayTopWords(Map<String, Integer> popularityCounter, boolean isPopular) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(popularityCounter.entrySet());
        sortedList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        System.out.println(isPopular ? "Топ-10 популярних слів:" : "Топ-10 непопулярних слів:");

        int limit = 10;
        int count = 0;

        for (Map.Entry<String, Integer> entry : sortedList) {
            if (count >= limit) {
                break;
            }
            if ((isPopular && entry.getValue() > 0) || (!isPopular && entry.getValue() == 0)) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " звернень");
                count++;
            }
        }
    }

    public static void main(String[] args) {
        // Task1
        System.out.println("\nTask1\n");

        Dock dock = new Dock(5);
        dock.simulate(100);


        //Task2
        System.out.println("\nTask2");

        Map<String, String> dictionary = new HashMap<>();
        Map<String, Integer> popularityCounter = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        boolean ch = false;
        while (!ch) {
            System.out.println("\nСловник англо-руський");
            System.out.println("1. Відобразити переклад слова");
            System.out.println("2. Додати або замінити переклад слова");
            System.out.println("3. Видалити переклад слова");
            System.out.println("4. Додати або замінити слово");
            System.out.println("5. Видалити слово");
            System.out.println("6. Топ-10 популярних слів");
            System.out.println("7. Топ-10 непопулярних слів");
            System.out.println("8. Вийти");

            System.out.print("Оберіть опцію: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Введіть слово для перекладу: ");
                    String wordToTranslate = scanner.nextLine();
                    String translation = dictionary.get(wordToTranslate);
                    if (translation != null) {
                        System.out.println("Переклад: " + translation);
                        incrementPopularity(popularityCounter, wordToTranslate);
                    } else {
                        System.out.println("Слово не знайдено в словнику");
                    }
                    break;

                case 2:
                    System.out.print("Введіть слово для перекладу: ");
                    wordToTranslate = scanner.nextLine();
                    System.out.print("Введіть переклад: ");
                    translation = scanner.nextLine();
                    dictionary.put(wordToTranslate, translation);
                    incrementPopularity(popularityCounter, wordToTranslate);
                    System.out.println("Переклад додано або замінено");
                    break;

                case 3:
                    System.out.print("Введіть слово для видалення перекладу: ");
                    wordToTranslate = scanner.nextLine();
                    if (dictionary.remove(wordToTranslate) != null) {
                        decrementPopularity(popularityCounter, wordToTranslate);
                        System.out.println("Переклад видалено");
                    } else {
                        System.out.println("Слово не знайдено в словнику");
                    }
                    break;

                case 4:
                    System.out.print("Введіть слово для додавання або заміни: ");
                    String newWord = scanner.nextLine();
                    System.out.print("Введіть переклад: ");
                    String newTranslation = scanner.nextLine();
                    dictionary.put(newWord, newTranslation);
                    incrementPopularity(popularityCounter, newWord);
                    System.out.println("Слово додано або замінено");
                    break;

                case 5:
                    System.out.print("Введіть слово для видалення: ");
                    String wordToDelete = scanner.nextLine();
                    if (dictionary.remove(wordToDelete) != null) {
                        decrementPopularity(popularityCounter, wordToDelete);
                        System.out.println("Слово видалено");
                    } else {
                        System.out.println("Слово не знайдено в словнику");
                    }
                    break;

                case 6:
                    displayTopWords(popularityCounter, true);
                    break;

                case 7:
                    displayTopWords(popularityCounter, false);
                    break;

                case 8:
                    System.out.println("Дякуємо за використання словника");
                    ch = true;
                    break;

                default:
                    System.out.println("Невірна опція. Спробуйте ще раз");
            }


            //Task3
            System.out.println("\nTask3\n");

            TaxDatabase taxDatabase = new TaxDatabase();

            taxDatabase.addPerson("001", "Ольга", "Київ");
            taxDatabase.addFine("001", "Парковка", 50.0);
            taxDatabase.addFine("001", "Привишення швидкості", 100.0);

            taxDatabase.addPerson("002", "Данііл", "Кременчук");
            taxDatabase.addFine("002", "Парковка", 40.0);

            taxDatabase.addPerson("003", "Богдан", "Львів");
            taxDatabase.addFine("003", "Парковка", 60.0);
            taxDatabase.addFine("003", "Привишення швидкості", 75.0);

            while (true) {
                System.out.println("\nМеню:");
                System.out.println("1. Вивести Базу Даних");
                System.out.println("2. Вивести Дані за Кодом");
                System.out.println("3. Вивести Дані за Типом Штрафу");
                System.out.println("4. Вивести Дані за Містом");
                System.out.println("5. Додати Особу");
                System.out.println("6. Додати Штраф");
                System.out.println("7. Видалити Штраф");
                System.out.println("8. Оновити Інформацію про Особу");
                System.out.println("9. Вихід");
                System.out.print("Виберіть опцію: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("База Даних:");
                        taxDatabase.printDatabase();
                        break;
                    case 2:
                        System.out.print("Введіть код особи: ");
                        String code = scanner.nextLine();
                        System.out.println("Дані для особи з кодом " + code + ":");
                        taxDatabase.printDataByCode(code);
                        break;
                    case 3:
                        System.out.print("Введіть тип штрафу: ");
                        String fineType = scanner.nextLine();
                        System.out.println("Дані для осіб із штрафами типу '" + fineType + "':");
                        taxDatabase.printDataByFineType(fineType);
                        break;
                    case 4:
                        System.out.print("Введіть місто: ");
                        String city = scanner.nextLine();
                        System.out.println("Дані для осіб з міста " + city + ":");
                        taxDatabase.printDataByCity(city);
                        break;
                    case 5:
                        System.out.print("Введіть код особи: ");
                        String newCode = scanner.nextLine();
                        System.out.print("Введіть ім'я особи: ");
                        String name = scanner.nextLine();
                        System.out.print("Введіть місто особи: ");
                        String newCity = scanner.nextLine();
                        taxDatabase.addPerson(newCode, name, newCity);
                        break;
                    case 6:
                        System.out.print("Введіть код особи: ");
                        String codeToAddFine = scanner.nextLine();
                        System.out.print("Введіть тип штрафу: ");
                        String fineTypeToAdd = scanner.nextLine();
                        System.out.print("Введіть суму штрафу: ");
                        double fineAmountToAdd = scanner.nextDouble();
                        taxDatabase.addFine(codeToAddFine, fineTypeToAdd, fineAmountToAdd);
                        break;
                    case 7:
                        System.out.print("Введіть код особи: ");
                        String codeToRemoveFine = scanner.nextLine();
                        System.out.print("Введіть індекс штрафу для видалення: ");
                        int fineIndexToRemove = scanner.nextInt();
                        taxDatabase.removeFine(codeToRemoveFine, fineIndexToRemove);
                        break;
                    case 8:
                        System.out.print("Введіть код особи: ");
                        String codeToUpdate = scanner.nextLine();
                        System.out.print("Введіть нове ім'я особи: ");
                        String newName = scanner.nextLine();
                        System.out.print("Введіть нове місто особи: ");
                        String newCityToUpdate = scanner.nextLine();
                        taxDatabase.updatePersonInfo(codeToUpdate, newName, newCityToUpdate);
                        break;
                    case 9:
                        System.out.println("Вихід з програми");
                        System.exit(0);
                    default:
                        System.out.println("Невірний вибір. Будь ласка, введіть дійсну опцію");
                }
            }
        }
    }
}