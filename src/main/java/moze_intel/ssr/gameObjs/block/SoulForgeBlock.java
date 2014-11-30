package moze_intel.ssr.gameObjs.block;

import java.util.Random;

import moze_intel.ssr.gameObjs.ObjHandler;
import moze_intel.ssr.gameObjs.tile.SoulForgeTile;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoulForgeBlock extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon forgeicon_on;
	private IIcon forgeicon_off;

	public SoulForgeBlock() {
		super(Material.rock);
		this.setBlockName("ssr_forge_block");
		this.setCreativeTab(ObjHandler.CREATIVE_TAB);
		this.blockHardness = 3.0F;
		this.blockResistance = 3.0F;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z,
			Random random) {
		int meta = world.getBlockMetadata(x, y, z);
		float f = (float) x + 0.5F;
		float f1 = (float) y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
		float f2 = (float) z + 0.5F;
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;

		if (meta == 1) {
			world.spawnParticle("smoke", (double) (f - f3), (double) f1,
					(double) (f2 + f4), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", (double) (f - f3), (double) f1,
					(double) (f2 + f4), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("smoke", (double) (f + f3), (double) f1,
					(double) (f2 + f4), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", (double) (f + f3), (double) f1,
					(double) (f2 + f4), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("smoke", (double) (f + f4), (double) f1,
					(double) (f2 - f3), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", (double) (f + f4), (double) f1,
					(double) (f2 - f3), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("smoke", (double) (f + f4), (double) f1,
					(double) (f2 + f3), 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", (double) (f + f4), (double) f1,
					(double) (f2 + f3), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if (world.isRemote) {
			return true;
		} else {
			SoulForgeTile tileentitySoulForge = (SoulForgeTile) world
					.getTileEntity(x, y, z);

			if (tileentitySoulForge != null) {
				player.func_146101_a(tileentitySoulForge);
			}

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new SoulForgeTile();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack itemStack) {

		world.setBlockMetadataWithNotify(x, y, z, itemStack.getItemDamage(), 2);

		if (itemStack.hasDisplayName()) {
			((SoulForgeTile) world.getTileEntity(x, y, z))
					.func_145951_a(itemStack.getDisplayName());
		}
	}

	public Item getItemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(ObjHandler.SOUL_FORGE);
	}

	public Item getItem(World world, int par2, int par3, int par4) {
		return Item.getItemFromBlock(ObjHandler.SOUL_FORGE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("furnace_top");
		this.forgeicon_on = iconRegister.registerIcon("ssr:forge_on");
		this.forgeicon_off = iconRegister.registerIcon("ssr:forge_off");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return this.blockIcon;
		} else if (side == 0) {
			return this.blockIcon;
		} else if (meta == 1) {
			return this.forgeicon_on;
		} else {
			return this.forgeicon_off;

		}
	}
}