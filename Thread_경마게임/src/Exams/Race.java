package Exams;

 

import java.util.*;



public class Race {

 

    public static void main(String[] args) {

        List<racer> list = new ArrayList<racer>();

        list.add(new racer("1번말"));

        list.add(new racer("2번말"));

        list.add(new racer("3번말"));

        list.add(new racer("4번말"));

        list.add(new racer("5번말"));

        list.add(new racer("6번말"));

        list.add(new racer("7번말"));

        list.add(new racer("8번말"));

        list.add(new racer("9번말"));

        list.add(new racer("10번말"));

     

        for (racer horse : list) {

            horse.start();

        }

        print pt = new print(list);

        pt.start();

    }

}

class print extends Thread {

    List<racer> list;

    public print(List<racer> h1) {

        this.list = h1;

    }

    @Override

    public void run() {

        String[] arr = new String[50];

        int rnk = 1;

        boolean ing = true;

        while (ing) {

            for (racer h2 : list) {

                if(h2.isGoal()==true) {

                    System.out.print(h2.getHName() + " : ");

                    for (int j = 0; j < 50; j++) {

                        arr[j] = "*";

                        System.out.print(arr[j]);

                    }

                    System.out.println();

                    continue;

                }



                System.out.print(h2.getHName() + " : ");

                for (int i = 0; i < 50; i++) {

                    arr[i] = "-";

                    if (h2.getLocation() == i) {

                        arr[i] = ">";

                    }

                }

                

                



                for (int j = 0; j < 50; j++) {

                    System.out.print(arr[j]);

                }

                System.out.println();

               
                if (h2.getLocation() >= 50) {

                    h2.setRank(rnk);

                    rnk++;

                    h2.setGoal(true);

                }

            } 

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            System.out.println("=========================================================");

  

            if (rnk==11) {

                

                System.out.println("=========경기 끝!=======");

                ing=false;

            }

        }// end while

        Collections.sort(list);

        for (racer h2 : list) {

            System.out.printf("%3d 등  :  %3s",h2.getRank(),h2.getHName());

            System.out.println();

        }

        System.exit(0);

    }

}


class racer extends Thread implements Comparable<racer> {

    private String name;

    private int rank = 0;

    private int location = 0;

    public volatile boolean goal = false; 

    

    public racer(String name) {

		super();

		this.name = name;

	}

    @Override

    public int compareTo(racer h1) {

    	return Integer.compare(this.rank, h1.rank);

    }

	@Override

    public void run() {

        int cnt = 0;

        while (true) {

            location += cnt;

            try {

                Thread.sleep(2000 * (int) (Math.random() * 4));

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

 

            if (location == 50) {

                break;

            }

            cnt++;

        }

    }

    public String getHName() {

        return name;

    }

     public void sethName(String name) {

     this.name = name;

     }

    public boolean isGoal() {

        return goal;

    }

    public void setGoal(boolean goal) {

        this.goal = goal;

    }

    public int getRank() {

        return rank;

    }

    public void setRank(int rank) {

        this.rank = rank;

    }

    public int getLocation() {

        return location;  

    }

}  