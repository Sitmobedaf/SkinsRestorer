/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */

package skinsrestorer.shared.format;

public class SkinProfile {

	public static final SkinProfile NONE = new SkinProfile() {
		@Override
		public boolean isValid() {
			return false;
		}
	};

	private long timestamp;
	private boolean isForced;
	private SkinProperty playerSkinData;

	private SkinProfile() {
	}

	public SkinProfile(SkinProperty skinData) {
		timestamp = System.currentTimeMillis();
		playerSkinData = skinData;
	}

	public SkinProfile(SkinProperty skinData, boolean isForced) {
		timestamp = System.currentTimeMillis();
		playerSkinData = skinData;
		this.isForced = isForced;
	}

	public SkinProfile(SkinProperty skinData, long creationTime, boolean isForced) {
		this(skinData, isForced);
		timestamp = creationTime;
	}

	public boolean isValid() {
		return (System.currentTimeMillis() - timestamp) <= (2 * 60 * 60 * 1000) || isForced();
	}

	public boolean isForced() {
		return isForced;
	}

	public void setForced() {
		isForced = true;
	}

	public long getCreationDate() {
		return timestamp;
	}

	public SkinProperty getPlayerSkinProperty() {
		return playerSkinData;
	}

}
