
interface NPC{
    void interact();
}

class Villager implements NPC{
    @Override
    public void interact(){
        System.out.println("Villager says hello!");
    }
}

class Guard implements NPC{
    @Override
    public void interact(){
        System.out.println("Guard says hello!");
    }
}

class NPCFactory{
    public NPC createNPC(String type){
        if(type.equals("villager")){
            return new Villager();
        } else if(type.equals("guard")){
            return new Guard();
        }
        return null;
    }
}

public class ProductFactory {
    public static void main(String[] args) {
        NPCFactory npcFactory = new NPCFactory();
        NPC villager = npcFactory.createNPC("villager");
        NPC guard = npcFactory.createNPC("guard");
        villager.interact();
        guard.interact();
    }
}