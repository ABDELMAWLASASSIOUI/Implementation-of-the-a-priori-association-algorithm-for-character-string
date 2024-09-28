package classtesting;

import java.util.*;

public class AprioriExample {

    private List<Set<String>> transactions;

    public AprioriExample() {
        transactions = new ArrayList<>();
    }

    public void loadTransactions() {
        transactions.add(new HashSet<>(Arrays.asList("milk", "bread")));
        transactions.add(new HashSet<>(Arrays.asList("milk", "bread", "butter")));
        transactions.add(new HashSet<>(Arrays.asList("bread", "butter")));
        transactions.add(new HashSet<>(Arrays.asList("milk", "butter")));
    }

    private Map<Set<String>, Integer> generateFrequent1Itemsets(double minSupport) {
        Map<Set<String>, Integer> frequent1Itemsets = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        for (Set<String> transaction : transactions) {
            for (String item : transaction) {
                Set<String> itemset = new HashSet<>(Collections.singletonList(item));
                counts.put(item, counts.getOrDefault(item, 0) + 1);
                frequent1Itemsets.put(itemset, counts.get(item));//add une seul item avec le support dans le transaction
            }
        }

        return pruneInfrequentItemsets(frequent1Itemsets, minSupport);
    }

    private Map<Set<String>, Integer> pruneInfrequentItemsets(Map<Set<String>, Integer> itemsets, double minSupport) {
        Map<Set<String>, Integer> frequentItemsets = new HashMap<>();

        int minSupportCount = (int) Math.ceil(minSupport * transactions.size());

        for (Map.Entry<Set<String>, Integer> entry : itemsets.entrySet()) {
            if (entry.getValue() >= minSupportCount) {
                frequentItemsets.put(entry.getKey(), entry.getValue());
            }
        }

        return frequentItemsets;
    }

    public static void main(String[] args) {
        AprioriExample example = new AprioriExample();
        example.loadTransactions();

        double minSupport = 0.5;
        Map<Set<String>, Integer> frequent1Itemsets = example.generateFrequent1Itemsets(minSupport);

        System.out.println("Frequent 1-itemsets:");
        for (Map.Entry<Set<String>, Integer> entry : frequent1Itemsets.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
