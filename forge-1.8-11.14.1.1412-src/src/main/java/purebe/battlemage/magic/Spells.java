package purebe.battlemage.magic;

import java.util.Arrays;
import java.util.List;

import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public final class Spells {
	public enum Spell {
		None, Cancel, Illuminate, ArtilleryCattle, Teleport;
		
		public static List<SpellSymbol> getIncantation(Spell spell) {
			switch (spell) {
			case Illuminate:
				return Arrays.asList(SpellSymbol.White, SpellSymbol.Nature, SpellSymbol.Chaos);
			case ArtilleryCattle:
				return Arrays.asList(SpellSymbol.White, SpellSymbol.Black, SpellSymbol.Chaos, SpellSymbol.Nature, SpellSymbol.Power);
			case Teleport:
				return Arrays.asList(SpellSymbol.Chaos, SpellSymbol.White, SpellSymbol.Chaos, SpellSymbol.White, SpellSymbol.Chaos);
			default:
				break;
			}
			return null;
		}
	}	
}
