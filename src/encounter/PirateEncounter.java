package encounter;

import game.Game;
import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class PirateEncounter extends Encounter {

    public PirateEncounter(World world,int strength){
        super(world,"You encounter a pirate ship" , new Solution[]{new FightSolution(strength)});
    }

    static class FightSolution implements Solution{
        int strength;
        public FightSolution(int strength){
            this.strength = strength;
        }
        @Override
        public String[] resolve(World w) {
            String[] results;
            // Negative if Pirate win Positive if Player wins
            int fightResult = ((int) (Math.random() * (w.player.skill * ((w.player.moral + 10) / 100) + strength))) - strength;
            if(fightResult < 0){
                results = new String[4];
                results[0] = "Your ship was badly damaged by the pirates/nand you lost some cargo";
                int damage= ((int) Math.random() * 15 + 5);
                w.player.health -= damage;
                results[1] = "Health: -" + damage;
                int moralloss = ((int) Math.random() * 5 + 5);
                w.player.moral -= moralloss;
                results[2] = "Moral: -" + moralloss;
                int foodloss = ((int) Math.random()  * 10 + 1);
                w.player.food -= foodloss;
                results[3] = "Food: -" + foodloss;
            }else{
                results = new String[1];
                results[0] = "YEEEEE";
            }

            return results;
        }

        @Override
        public String getText(){
            return "Fight";
        }
    }
}
