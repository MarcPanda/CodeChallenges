import java.math.BigInteger;
import java.util.Scanner;

public class sequences {
	static Scanner in = new Scanner(System.in);
	static long modulo = 1_000_000_007;
	static BigInteger bigModulo = BigInteger.valueOf(modulo);
	
	public static void main(String[] args) {
		
		char[] sequenceQM = in.nextLine().toCharArray();
		
		
		
		/*
		 * - Stocker dans p la position de tous les '?'
		 * - Compter le nombre de permutation pour tous les 0 et 1 prédéfinis dans la séquence en
		 * entrée. Pour simplifier notre réflexion, une permutation revient à un mouvement d’un
		 * zéro vers la gauche. Pour le début, le comptage ne prend pas en compte les '?', qui
		 * seront traités bien plus tard.
		 */
		int[] p = new int[sequenceQM.length]; // tableau qui stocke la position de tous les '?'
		int k = 0; // le nombre de '?'
		long swapCountStaticBits1Seq = 0; // le nombre de permutation pour tous les 0 et 1 prédéfinis dans la séquence en entrée
		int target = 0; // la position juste après tous les zéros prédéfinis qui sont déplacés à gauche.
		// cette valeur sers à déterminer où les éventuels 0 qui remplacent les '?' se placeront.
		
		// on parcours la séquence en entrée pour calculer toutes ces variables au-dessus.
		for (int i = 0; i < sequenceQM.length; i++) {
			if (sequenceQM[i] == '?')
				p[k++] = i;
			else if (sequenceQM[i] == '0') {
				swapCountStaticBits1Seq += i - target;
				target++;
			}
		}
		
		
		
		
		/*
		 * Premier cas: k == 0.
		 * 
		 * Il n’y a pas de '?' dans la séquence d’entrée, donc celle-ci correspond à l’unique
		 * séquence binaire à traiter.
		 * Dans ce cas, seul le nombre de permutation calculé au dessus est nécessaire.
		 */
		if (k == 0) {
			// on oublie pas le modulo :
			System.out.println(swapCountStaticBits1Seq % modulo);
			return;
		}
		
		
		
		/* 
		 * À partir de là, on sait qu’il y a plus d’une séquence binaire.
		 */
		
		
		
		BigInteger twoPowK = BigInteger.TWO.pow(k); // le nombre de séquence binaire, ici 2^k
		
		
		/* Ici on multiplie le nombre de permutation calculée au dessus, par le nombre de séquence.
		 * En effet, toutes les séquences binaires contiennent les 0 et 1 de la séquence en entrée,
		 * en plus des 0 et des 1 qui viennent remplacer les '?', et dont on calculera leurs
		 * permutations en-dessous.
		 */
		BigInteger swapCountStaticBitsTot = twoPowK.multiply(BigInteger.valueOf(swapCountStaticBits1Seq));
		
		
		
		
		
		/*
		 * Maintenant, on s’occupe de calculer le nombre de déplacement relatif aux '?' de la
		 * séquence d’entrée.
		 * Attention, cette partie là sera très mathématique !
		 * 
		 * Suite à une réflexion qu’il faudrait que j’explique un jour (en gros j’y ai réfléchi
		 * cette nuit), j’ai déterminé une formule qui indique la moyenne du nombre de permutation
		 * pour chaque point d’interrogation de chaque séquence binaire.
		 * En considérant les variables suivantes :
		 * - le tableau p défini ci-dessus, stockant les positions des points d’interrogation,
		 * - la variable target défini ci-dessus,
		 * - et une variable i indiquant le combientième point d’interrogation il s’agit
		 * La formule est la suivante :
		 *     (p[i] - target - i/2) / 2
		 * - p[i] donne la position du i-ème point d’interrogation
		 * - i/2 détermine le décalage moyen entre target et la vraie position cible, provoquée par
		 *   le fait qu’il y a d’autres '?' avant. Pour le 1er '?', i vallant 0, il n’y a donc pas
		 *   d’influence.
		 * - la division par 2 globale est dû au fait que pour un '?', seul la moitié des séquences
		 *   binaires auront un 0 (l’autre moitié un 1) à la place de ce '?'. Comme nous simpli-
		 *   fions notre réflexion en parlant de « déplacements de '0' » au lieu de permutations,
		 *   nous pouvons alors ignorer les 1 de nos calculs.
		 * 
		 * Maintenant, prenons en compte ce calcul pour tous les points d’interrogation d’une
		 * séquence :
		 *     Σ_(i=0→k-1), (p[i] - target - i/2) / 2
		 * Maintenant, il reste à multiplier par le nombre de séquence, 2^k :
		 *     (Σ_(i=0→k-1), (p[i] - target - i/2) / 2) * 2^k
		 * sachant que k >= 1 (le cas k == 0 a déjà été traité au-dessus).
		 * 
		 * Comme kattis impose un temps d’exécution très court de notre programme, on doit
		 * optimiser. En effet, le calcul de la Σ utilise une boucle, qui peut itérer jusqu’à 500k
		 * fois. Le fait qu’on utilise des très grand nombres (BigInteger) dans le calcul pose
		 * aussi soucis, donc nous devons les sortir de la boucle :
		 *     (Σ_(i=0→k-1), p[i] - target - i/2)   / 2 * 2^k                     // on sors '/ 2' de la somme. On peut simplifier (2^k)/2
		 *     (Σ_(i=0→k-1), p[i] - target - i/2)   * 2^(k-1)                     // on simplifie '/ 2 * 2^k' en '2^(k-1)'
		 *     ((Σ_(i=0→k-1), p[i] - i/2) - k*target) * 2^(k-1)                   // on sors '-target' de la somme, donc on le multiplie par k
		 *     ((Σ_(i=0→k-1), p[i]) - (Σ_(i=0→k-1), i/2) - k*target) * 2^(k-1)    // on converti la Σ de soustraction en soustraction de Σ
		 *     ((Σ_(i=0→k-1), p[i]) - (Σ_(i=0→k-1), i)/2 - k*target) * 2^(k-1)    // on sors '/2' de la somme
		 *     ((Σ_(i=0→k-1), p[i]) - ((k-1)*((k-1)+1)/2)/2 - k*target) * 2^(k-1) // on optimise le calcul du nombre triangulaire (une boucle en moins)
		 *     ((Σ_(i=0→k-1), p[i]) - ((k-1)*k/2)/2 - k*target) * 2^(k-1)         // on simplifie encore
		 * On se retrouve avec un problème : pour certaines valeurs de k, la formule ((k-1)*k/2)/2
		 * donnera des valeurs non-entière, ce qu’on ne veut pas pour éviter les pertes de
		 * précision, surtout pour des grands nombres. On doit alors déplacer la division par 2
		 * du nombre triangulaire. On multiplie par 2 ce qu’il y a à gauche de la multiplication,
		 * et on divise par 2 ce qu’il y a à droite :
		 *     ((Σ_(i=0→k-1), p[i])*2 - (k-1)*k/2 - k*target*2) * 2^(k-1) / 2
		 *     ((Σ_(i=0→k-1), p[i])*2 - (k-1)*k/2 - k*target*2) * 2^(k-2)
		 * 
		 * Il reste maintenant un dernier problème de nombre entier avec le 2^(k-2), quand k == 1 (2^(1-2) == 2^-1 == 0.5).
		 * On peut alors traiter la condition k == 1 séparément :
		 *     ((Σ_(i=0→k-1), p[i]) - ((k-1)*k/2)/2 - k*target) * 2^(k-1)
		 *     ((Σ_(i=0→0), p[i]) - ((1-1)*1/2)/2 - 1*target) * 2^(1-1)
		 *     (p[0] - target - (0*1/2)/2) * 2^0
		 *     (p[0] - target - 0) * 1
		 *     p[0] - target
		 *     
		 * En résumé, pour calculer le nombre de déplacement relatif aux '?' de la séquence
		 * d’entrée, pour toutes les séquences binaires, nous avons les formules suivantes :
		 *     pour k == 1: p[0] - target
		 *     pour k >= 2: ((Σ_(i=0→k-1), p[i])*2 - k*target*2 - (k-1)*k/2) * 2^(k-2)
		 */
		
		if (k == 1) {
			int swapCountQMBitsTot = p[0] - target;
			// on oublie pas d’ajouter les 0 et 1 prédéfinis dans la séquence d’entrée :
			BigInteger swapCountTot = swapCountStaticBitsTot.add(BigInteger.valueOf(swapCountQMBitsTot));
			// et de faire le modulo :
			System.out.println(swapCountTot.mod(bigModulo));
			return;
		}
		
		
		/*
		 * À partir d’ici, on a k >= 2, donc on applique la formule :
		 *     (  (Σ_(i=0→k-1), p[i]) * 2
		 *      - k * target * 2
		 *      - (k - 1) * k / 2
		 *     )
		 *     * 2^(k-2)
		 */
		long sumOfP = 0;
		for (int i = 0; i < k; i++) {
			sumOfP += p[i];
		}
		long swapCountQMBits1Seq = sumOfP * 2 - (long)k * target * 2 - ((long)k - 1) * k / 2;
		BigInteger swapCountQMBitsTot = BigInteger.TWO.pow(k - 2).multiply(BigInteger.valueOf(swapCountQMBits1Seq));

		// on oublie pas d’ajouter les 0 et 1 prédéfinis dans la séquence d’entrée :
		BigInteger swapCountTot = swapCountQMBitsTot.add(swapCountStaticBitsTot);
		// et de faire le modulo :
		System.out.println(swapCountTot.mod(bigModulo));
	}
	
	
}
