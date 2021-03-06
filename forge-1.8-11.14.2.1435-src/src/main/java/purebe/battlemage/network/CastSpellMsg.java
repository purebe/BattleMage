package purebe.battlemage.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import purebe.battlemage.magic.ArtilleryCattleSpell;
import purebe.battlemage.magic.FlySpell;
import purebe.battlemage.magic.GustSpell;
import purebe.battlemage.magic.IlluminateSpell;
import purebe.battlemage.magic.MagicalWallSpell;
import purebe.battlemage.magic.Spell;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.TeleportSpell;

public class CastSpellMsg implements IMessage {
	SpellName spell;
	Vec3  playerLookat;
	
	public CastSpellMsg() {}
	
	public CastSpellMsg(SpellName spell, Vec3 playerLookat) {
		this.spell = spell;
		this.playerLookat = playerLookat;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		spell = SpellName.values()[buf.readInt()];
		playerLookat = new Vec3(buf.readFloat(), buf.readFloat(), buf.readFloat());
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(spell.ordinal());
		buf.writeFloat((float)playerLookat.xCoord);
		buf.writeFloat((float)playerLookat.yCoord);
		buf.writeFloat((float)playerLookat.zCoord);
	}
	
	public static class CastSpellMsgHandler implements IMessageHandler<CastSpellMsg, IMessage> {
		@Override
		public IMessage onMessage(CastSpellMsg msg, MessageContext context) {
			final CastSpellMsg finalMsg = msg;
			final MessageContext finalContext = context;
			context.getServerHandler().playerEntity.getServerForPlayer().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("Casting spell: " + finalMsg.spell.toString());
					Spell spell = null;
					switch (finalMsg.spell) {
						case Illuminate:
							spell = new IlluminateSpell(finalContext.getServerHandler().playerEntity);
							break;
						case ArtilleryCattle:
							spell = new ArtilleryCattleSpell(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
							break;
						case Teleport:
							spell = new TeleportSpell(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
							break;
						case MagicalWall:
							spell = new MagicalWallSpell(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
							break;
						case Gust:
							spell = new GustSpell(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
							break;
						case Fly:
							spell = new FlySpell(finalContext.getServerHandler().playerEntity, true);
							break;
						case EndFlight:
							spell = new FlySpell(finalContext.getServerHandler().playerEntity, false);
							break;
						default:
							break;
					}
					if (spell != null) {
						spell.cast();
					}
				}
			});
			return null;
		}
	}
}