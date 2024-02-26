package org.example.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SuperMarketDemo {
    public static class SuperMarket {
        public static Map<String, Fruit> fruitMap = new HashMap<>();

        public boolean hasSalesPromotion = false;

        public void setSalesPromotion(boolean hasSalesPromotion) {
            this.hasSalesPromotion = hasSalesPromotion;
        }

        static {
            fruitMap.put("apple", new Apple());
            fruitMap.put("strawberry", new Strawberry());
            fruitMap.put("mango", new Mango());
        }

        public double buyFruit(Map<String, Integer> buyMap, boolean hasDiscount) {
            //key对应水果名称
            //value对应斤数目
            double count = 0;
            Iterator<Map.Entry<String, Integer>> iterator = buyMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();
                String name = next.getKey();
                Integer buyNum = next.getValue();
                if (fruitMap.containsKey(name)) {
                    Fruit fruit = fruitMap.get(name);
                    if (hasDiscount) {
                        count += buyNum * (fruit.getPrice() * fruit.getDiscount());
                    } else {
                        count += buyNum * fruit.getPrice();
                    }
                }
            }

            if (hasSalesPromotion) {
                count = salesPromotion(count);
            }
            return count;
        }

        public double salesPromotion(double count) {
            return count >= 100 ? count - 10 : count;
        }

        public interface Fruit {
            public double getDiscount();

            public String getName();

            public double getPrice();
        }

        public static class Apple implements Fruit {
            public int price = 8;

            @Override
            public double getDiscount() {
                return 1;
            }

            @Override
            public String getName() {
                return "apple";
            }

            @Override
            public double getPrice() {
                return price;
            }
        }

        public static class Strawberry implements Fruit {
            int price = 13;

            @Override
            public String getName() {
                return "strawberry";
            }

            @Override
            public double getPrice() {
                return price;
            }

            @Override
            public double getDiscount() {
                return 0.8;
            }
        }

        public static class Mango implements Fruit {
            public int price = 20;

            @Override
            public String getName() {
                return "mango";
            }

            @Override
            public double getPrice() {
                return price;
            }

            @Override
            public double getDiscount() {
                return 1;
            }
        }
    }


    public static void main(String[] args) {
        SuperMarket superMarket = new SuperMarket();
        //顾客A
        Map<String, Integer> buyA = new HashMap<>();
        buyA.put("apple", 10);
        buyA.put("strawberry", 10);

        double countA = superMarket.buyFruit(buyA, false);
        System.out.println("顾客A=" + countA);


        //顾客B
        Map<String, Integer> buyB = new HashMap<>();
        buyB.put("apple", 10);
        buyB.put("strawberry", 10);
        buyB.put("mango", 10);


        double countB = superMarket.buyFruit(buyB, false);
        System.out.println("顾客B=" + countB);

        //顾客C
        Map<String, Integer> buyC = new HashMap<>();
        buyC.put("apple", 10);
        buyC.put("strawberry", 10);
        buyC.put("mango", 10);

        double countC = superMarket.buyFruit(buyC, true);
        System.out.println("顾客C=" + countC);

        //顾客D
        Map<String, Integer> buyD = new HashMap<>();
        buyD.put("apple", 10);
        buyD.put("strawberry", 10);
        buyD.put("mango", 10);

        superMarket.setSalesPromotion(true);
        double countD = superMarket.buyFruit(buyD, true);
        System.out.println("顾客D=" + countD);
    }
}
