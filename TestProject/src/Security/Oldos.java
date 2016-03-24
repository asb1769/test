/**
 * Created by ASB on 29.02.2016.
 */
package Security;
import Display.*;
import java.util.ArrayList;

public class Oldos {

    private static enum Ability {unknown, allow, deny}

    public interface Visible {
        static View v = new View();
        void show();
        //void hide();
    }


    public class Subject implements Visible {
        protected String name;

        public Subject(String x) {
            name = x;
            show();
        }

        public void setName(String x) { name = x; }
        public String getName() { return name; }

        public void show() {
            v.out(name+": Привет! Я " + name+" )");
        }

        public void getResource(Resource r) {
            v.out(name + ": О! А возьму-ка я себе " + r.getName());
            r.setOwner(this);
        }

        public void giveResourse(Resource r, Subject lucky) {
            if (r.getOwner()==this) {
                r.setOwner(lucky);
            } else {
              v.out("Не могу. Это не моё!");
            }
        }

        public void dropResource(Resource r) {
            v.out(name + ": Да ну его, этот " + r.getName());
            r.setOwner(null);
        }

        public void requestResource(Resource res, Subject owner) {
            //SendRequest
        }
    }

    public class User extends Subject {
        private String password;

        public User(String name) {
            super(name);
            password = "1234";
        }

        public void setPassword(String s) {
            password = s;
        }
    }

    public class Resource implements Visible {
        private String name;
        private Subject owner;
        private Action[] actions;

        public Resource(String x) {
            name = x;
            show();
        }
        public String getName() { return name; }
        public void setName(String x) {
            name = x;
        }
        public void setOwner(Subject newOwner) {
            if (newOwner != owner) {
                if (owner != null & newOwner != null) {
                    v.out("Security: Оппа! Куууда? " + newOwner.getName() + ", не трожьте, эта не ваше, ваще!");
                } else {
                    owner = newOwner;
                    v.out("Security: Теперь ресурс " + name + " принадлежит: " + getOwnerName());
                }
            }
        }
        public Subject getOwner() {
            return owner;
        }
        public String getOwnerName() {
            if (owner != null) {
                return owner.getName();
            }
            else {
                return "ничей";
            }
        }
        //@Override
        public void show() {
            v.out("Security: Здесь " + name + ", принадлежит: " + getOwnerName());
        }

        public class Action {
            private String name;
            public void doAction() {

            }
            //public Action(String x) { name = x; }
        }

/*       public class Activity {
            private String name;
            public Activity(String x) { name = x; }
        }*/

    }

    protected class Permission {
        private Ability ability;

    }

    public class Role {
        private String name;
          //enabledActions;

        public Role(String x) {
            name = x;
        }
        public String getName() { return name;}
        public void setName(String x) { name= x;}

    }


//**************************************************************************************

    public ArrayList<Resource> resourcesList = new ArrayList();
    public ArrayList<User> usersList = new ArrayList();


    public void newResource(String resourceName) {
        resourcesList.add(new Resource(resourceName));
    }

    public void newUser(String userName) {
        usersList.add(new User(userName));
    }

    public User getUserByName(String userName) {
        for (int i=0; i<usersList.size();i++) {
            if (usersList.get(i).getName().equals(userName)) {
                return usersList.get(i);
            }
        }
        return null;
    }

    public Resource getResourceByName(String resourceName) {
        for (int i=0; i<resourcesList.size();i++) {
            if (resourcesList.get(i).getName().equals(resourceName)) {
                return resourcesList.get(i);
            }
        }
        return null;
    }


    public void run() {

    }

}

