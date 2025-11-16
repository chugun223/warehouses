import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SackWarehouse sackWarehouse = new SackWarehouse();
        BoxWarehouse boxWarehouse = new BoxWarehouse();
        sackWarehouse.items.addAll(List.of(
                new Sack(CargoType.Sand, 20),
                new Sack(CargoType.Plaster, 25),
                new Sack(CargoType.Cement, 30),
                new Sack(CargoType.Sand, 15)));
        boxWarehouse.items.addAll(List.of(
                new Box(14, 5, 20, true),
                new Box(112, 43, 66, false),
                new Box(10, 5, 3, false),
                new Box(17, 8, 1, true)));
        main : while (true) {
            System.out.println("\nкоманды");
            System.out.println("1 - показать товары на складе | тип ввода:<1> <Sack/Box>");
            System.out.println("2 - сделать заказ мешка | тип ввода:<2> <Cement/Sand/Plaster> <вес> <VIP:true/false>");
            System.out.println("3 - выполнить заказ | тип ввода:<3> <Sack/Box>");
            System.out.println("4 - показать количество хрупких коробок");
            System.out.println("5 - сделать заказ коробки | тип ввода:<6> <длина> <ширина> <высота> <хрупкая:true/false> <VIP:true/false>");
            System.out.println("6 - показать коробки отсортированные по размеру");
            System.out.println("7 - показать типы мешков, которых нет на складе");
            System.out.println("8 - показать количество мешков по типам");
            System.out.println("0 - выход");
            System.out.print("введите команду:");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length == 0) continue;

            int command;
            try {
                command = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("неверный ввод команды");
                continue;
            }

            switch (command) {
                case 1 -> {
                    if (parts.length != 2) {
                        System.out.println("укажите склад: Sack или Box");
                        break;
                    }
                    String type = parts[1];
                    if (type.equalsIgnoreCase("Sack")) {
                        System.out.println("\nтовары на складе мешков:");
                        for (Sack s : sackWarehouse.getItems())
                            System.out.println("- " + s.cargoType + " (" + s.getWeightInKG() + " кг)");
                    } else if (type.equalsIgnoreCase("Box")) {
                        System.out.println("\nтовары на складе коробок:");
                        for (Box b : boxWarehouse.getItems()) {
                            System.out.println("- Длина: " + b.getLength() + " Ширина: " + b.getWidth() + " Высота: " + b.getHeight() +", хрупкая=" + b.isFragile);
                        }
                    } else {
                        System.out.println("неизвестный тип склада");
                    }
                }

                case 2 -> {
                    if (parts.length != 4) {
                        System.out.println("использование: 2 <тип> <вес> <VIP:true/false>");
                        break;
                    }
                    try {
                        CargoType cargoType = CargoType.valueOf(parts[1]);
                        double weight = Double.parseDouble(parts[2]);
                        boolean vip = Boolean.parseBoolean(parts[3]);
                        Sack sack = new Sack(cargoType, weight);
                        Order<Sack> order = new Order<>(sack, vip);
                        if (vip) sackWarehouse.addVIPOrder(order);
                        else sackWarehouse.addOrder(order);
                        System.out.println("\nзаказ добавлен: " + cargoType + " " + weight + " кг, VIP=" + vip);
                    } catch (Exception e) {
                        System.out.println("ошибка парсинга команды: " + e.getMessage());
                    }
                }

                case 3 -> {
                    if (parts.length != 2) {
                        System.out.println("укажите склад: Sack или Box");
                        break;
                    }
                    String type = parts[1];
                    if (type.equalsIgnoreCase("Sack")) {
                        Sack s = sackWarehouse.processOrder();
                        if (s != null) System.out.println("\nвыполнен заказ на мешок: " + s.cargoType + " " + s.getWeightInKG() + " кг");
                    } else if (type.equalsIgnoreCase("Box")) {
                        Box b = boxWarehouse.processOrder();
                        if (b != null) System.out.println("\nвыполнен заказ на коробку: Длина: " + b.getLength() + " Ширина: " + b.getWidth() + " Высота: " + b.getHeight() + ", хрупкая=" + b.isFragile);
                    } else {
                        System.out.println("неизвестный тип склада");
                    }
                }

                case 4 -> {
                    System.out.println("\nхрупких коробок: " + boxWarehouse.getFragileAmount());
                }

                case 5 -> {
                    if (parts.length != 6) {
                        System.out.println("\nиспользование: 6 <длина> <ширина> <высота> <хрупкая:true/false> <VIP:true/false>");
                        break;
                    }
                    try {
                        double length = Double.parseDouble(parts[1]);
                        double width = Double.parseDouble(parts[2]);
                        double height = Double.parseDouble(parts[3]);
                        boolean fragile = Boolean.parseBoolean(parts[4]);
                        boolean vip = Boolean.parseBoolean(parts[5]);
                        Box box = new Box(length, width, height, fragile);
                        Order<Box> order = new Order<>(box, vip);
                        if (vip) boxWarehouse.addVIPOrder(order);
                        else boxWarehouse.addOrder(order);
                        System.out.println("\nзаказ на коробку добавлен: Длина: " + length + " Ширина: " + width + " Высота: " + height + ", хрупкая=" + fragile + ", VIP=" + vip);
                    } catch (Exception e) {
                        System.out.println("ошибка парсинга команды: " + e.getMessage());
                    }
                }

                case 6 -> {
                    System.out.println("\nкоробки, отсортированные по максимальному размеру:");
                    for (Box b : boxWarehouse.getSortedByLength()) {
                        System.out.printf("размер: %.2f, хрупкая=%b\n", b.getMaxSize(), b.isFragile);
                    }
                }

                case 7 -> {
                    System.out.println("\nтипы мешков, которых нет на складе: " + sackWarehouse.getEmptyTypes());
                }

                case 8 -> {
                    System.out.println("\nколичество мешков по типам:");
                    Map<CargoType, List<Sack>> grouped = sackWarehouse.getSacksByTypes();
                    for (var entry : grouped.entrySet()) {
                        System.out.printf("%s: %d штук.\n", entry.getKey(), entry.getValue().size());
                    }
                }

                case 0 -> {
                    System.out.println("выход");
                    break main;
                }

                default -> System.out.println("неизвестная команда");
            }
        }
    }
}