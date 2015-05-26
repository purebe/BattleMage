package purebe.battlemage.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import purebe.battlemage.magic.ArtilleryCattle;
import purebe.battlemage.magic.ISpell;
import purebe.battlemage.magic.Illuminate;
import purebe.battlemage.magic.Spells.Spell;
import purebe.battlemage.magic.Teleport;

public class CastSpellMsg implements IMessage {
	Spell spell;
	Vec3  playerLookat;
	
	public CastSpellMsg() {}
	
	public CastSpellMsg(Spell spell, Vec3 playerLookat) {
		this.spell = spell;
		this.playerLookat = playerLookat;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		spell = Spell.values()[buf.readInt()];
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
					ISpell spell = null;
					switch (finalMsg.spell) {
					case Illuminate:
						spell = new Illuminate(finalContext.getServerHandler().playerEntity);
						break;
					case ArtilleryCattle:
						spell = new ArtilleryCattle(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
						break;
					case Teleport:
						spell = new Teleport(finalContext.getServerHandler().playerEntity, finalMsg.playerLookat);
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