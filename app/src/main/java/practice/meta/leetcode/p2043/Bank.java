package practice.meta.leetcode.p2043;

class Bank {

    private long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;    
    }
    
    public boolean transfer(int account1, int account2, long money) {
        int src = account1 - 1;
        int tgt = account2 - 1;

        if (src < 0 || src >= balance.length || tgt < 0 || tgt >= balance.length ) {
            return false;
        }

        if (balance[src] >= money) {
            balance[src] -= money;
            balance[tgt] += money;
            return true;
        }

        return false;
    }
    
    public boolean deposit(int account, long money) {
        int tgt = account - 1;

        if (tgt < 0 || tgt >= balance.length ) {
            return false;
        }

        balance[tgt] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        int tgt = account - 1;

        if (tgt < 0 || tgt >= balance.length ) {
            return false;
        }

        if (balance[tgt] < money) {
            return false;
        }

        balance[tgt] -= money;
        return true;
    }
}
