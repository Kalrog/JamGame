package encounter;

import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class PirateEncounters {

    int strength;

    class FightSolution implements Solution{
        @Override
        public String[] resolve(World w) {
            // Negative if Pirate win Positive if Player wins
            int fightResult = ((int) (Math.random() * (w.player.skill * ((w.player.moral + 10) / 100) + PirateEncounters.this.strength))) - PirateEncounters.this.strength;


            return null;
        }

        @Override
        public String getText(){
            return "Fight";
        }
    }
}
