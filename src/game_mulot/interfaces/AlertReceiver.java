package game_mulot.interfaces;

/**
 * Interface pour les objets susceptibles de recevoir et de traiter des alertes.
 * 
 *
 */
public interface AlertReceiver {

	/**
	 * Permet de recevoir une alerte et de décider de la traiter ou non.
	 * 
	 * @param alert
	 *            - l'alert reçue.
	 */
	public void receivedAlert(Enum<?> alert);

	/**
	 * Permet de traiter une alerte.
	 * 
	 * @param alert
	 *            - l'alerte à traiter.
	 */
	public void dealAlert(Enum<?> alert);
}
