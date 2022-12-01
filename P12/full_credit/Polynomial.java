import java.util.Objects;
import java.util.ArrayList;
import java.util.ArrayDeque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.lang.Thread;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Polynomial {
    public static boolean log = false;   // Set true to print log messages
    public static synchronized void LOG(String s) {
        if(log) System.err.println("==> " + s);
    }

    public Polynomial(double ... d) { // accept any number of parameters into d[]
        if(d.length % 2 > 0) throw new RuntimeException("Odd number of Polynomial parameters: " + d.length);
        for(int i=0; i<d.length; i += 2) addTerm(d[i], d[i+1]);
    }
    public Polynomial(BufferedReader br) throws IOException {
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) terms.add(new Term(br));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write("" + terms.size() + "\n");
        for(Term term : terms) term.save(bw);
    }
    public void addTerm(double coefficient, double exponent) {
        terms.add(new Term(coefficient, exponent));
    }
    public double eval(double x) {
        double y = 0;
        for(Term term : terms) y += term.eval(x);
        return y;
    }
    public void solve(double min, double max, int nthreads, double slices, double precision) {
        roots.clear();
        double nextMin = min;
        double nextMax = min + (max - min)/nthreads;
        Thread[] threads = new Thread[nthreads];
        ArrayDeque work = new ArrayDeque();
        for(int i=1;i<=nthreads;++i){
            work.addLast(nextMin);
            work.addLast(nextMax);
            work.addLast(i);
            // System.out.println("    -Available processors: " + java.lang.Runtime.getRuntime().availableProcessors());
            // System.out.println("    -Minimum this thread: " + nextMin);
            // System.out.println("    -Maximum this thread: " + nextMax + "\n");
            Runnable runnable = new Runnable() {
                public void run() {
                    solveRecursive((double)work.removeFirst(),(double)work.removeFirst(),(int)work.removeFirst(),(slices/nthreads),precision,0);
                }
            };
            threads[i-1] = new Thread(runnable);
            threads[i-1].start();
            nextMin += (max - min)/nthreads;
            nextMax += (max - min)/nthreads;
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error: Thread was interrupted.");
            }
        }
    }

    public Object[] roots() {
        return roots.toArray();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Term term : terms) sb.append(term.toString());
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        Polynomial p = (Polynomial) o;
        if(p.terms.size() != this.terms.size()) return false;
        for(int i=0; i<terms.size(); ++i)
            if(!p.terms.get(i).equals(terms.get(i))) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(terms);
    }
    private static final double MAX_RECURSIONS = 20;
    private void solveRecursive(double min, double max, int threadID, double slices, double precision, int recursion) {
        LOG("ThreadID " + threadID + " recursion " + recursion + " at [" + min + "," + max + "]");    
        double delta = (max - min) / slices;
        double x1 = min;
        double y1 = eval(min);
        double x2 = x1 + delta;
        double y2;

        while(x1 < max) {
            y2 = eval(x2);
            if(Math.signum(y1) != Math.signum(y2)) {
                if((Math.abs(eval(x1+x2)/2) > precision) && (Math.abs(x2 - x1) > precision) && (recursion < MAX_RECURSIONS)) {
                    solveRecursive(x1, x2, threadID, Math.min(slices, (x2-x1)/precision), precision, recursion+1); // recurse for more precision
                } else {
                    mutex.lock();
                    try {
                        //System.out.println("ThreadID " + threadID + ": Root found: " + ((x1+x2)/2));
                        roots.add((x1+x2)/2);
                    } finally {
                        mutex.unlock();
                    }
                }
            }
            x1 = x2; 
            x2 = x1 + delta;
            y1 = y2;
        }
        //howareyoudoingthat+;
    }

    //public int howareyoudoingthat = 0;
    private Lock mutex = new ReentrantLock();
    private ArrayList<Term> terms = new ArrayList<>();
    private ArrayList<Double> roots = new ArrayList<>();
    
}
