package org.gameprototype.base.redis;

public class KeysTools {

	public static class ACCOUNTKEYS{
		
		/**
		 * 根据accountNAME得到对应key值
		 * @param account
		 * @return
		 */
		public static String accountKey(String account){
			
			return "ACCCOUNT_NAME_"+account;
		}
		
		/**
		 * 根据accountID得到对应的key值
		 * @param account
		 * @return
		 */
		public static String accountIdKEYS(Integer id){
			return "ACCCOUNT_ID_"+id;
		}
		
	}

	public static class PlayerKeys {

		public static String playerEquipListKey(Integer playerRoleId) {
			return "PLAYER_EQUIP_ROLE_ID_" + playerRoleId;
		}

		public static String playerEquipIDKey(Integer playerEquipId) {
			return "PLAYER_EQUIP_ID_" + playerEquipId;
		}

		public static String playerModelIDKey(Integer playerModelId) {
			return "PLAYER_MODEL_ID_" + playerModelId;
		}

		public static String playerModelListKey() {
			return "PLAYER_MODEL_LIST";
		}

		public static String playerRoleIDKey(Integer playerRoleId){
			return "PLAYER_ROLE_ID_" + playerRoleId;
		}
		
		public static String playerRoleNameKey(String playerRoleName){
			return "PLAYER_ROLE_NAME_" + playerRoleName;
		}
		
		
	}
	
	public static class ITEMKEYS{
		
		/**
		 * 根据ITEMKEYS得到对应key值
		 * @param id
		 * @return
		 */
		public static String itemKey(int id){
			
			return "ITEM_ID_"+id;
		}
		
		/**
		 * 根据itemtype、offset得到对应key值
		 * @param id
		 * @return
		 */
		public static String itemlistKey(int itemtype,int itemoffset){
			
			return "ITEMLIST_TYPE_"+itemtype+"_OFFSET_"+itemoffset;
		}
		
	}


	public static class COMBATKEYS {
	    /**
        * 根据combatName得到对应的key值
        * @param combatName
        * @return
        */
       public static String combatNameKey(String combatName) {

           return "COMBAT_NAME_" + combatName;
       }

		/**
		 * 根据combatID得到对应的key值
		 * @param combatId
		 * @return
		 */
		public static String combatKeys(String combatId) {

			return "COMBAT_ID_" + combatId;
		}

	}
	

    public static class MAPKEY {

        /**
         * 根据mapid得到对应的key值
         * @param mapid
         * @return
         */
        public static String mapKey(Integer id) {

            return "MAP_ID_" + id.toString();
        }
        /**
         * 根据mapid得到对应的角色id值
         * @param mapid
         * @return
         */
        public static String mapKeyRole(Integer id) {

            return "MAP_ROLE_" + id.toString();
        }
        
        public static String MAPLIST = "MAPLIST";
    }
}
