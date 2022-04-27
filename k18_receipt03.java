import java.text.DecimalFormat;		// ���� ��� ���Ŀ� �޸��� �߰��ϱ� ���� Ŭ����
import java.text.SimpleDateFormat;	// ���� ��¥,�ð� ��� ��� ���� ���� Ŭ����
import java.util.ArrayList;			// ArrayList -> ��ǰ�� �鼼 ���� ǥ�� �� ��¿� ��ǰ�� �����.
import java.util.Calendar;			// ���� ��¥,�ð� �޾ƿ��� ���� Ŭ����
//KOPO18 ������ / 2022.04.26
//5��_������ ����_#3-3. ������ (3) �̸�Ʈ (�鼼,���� ����)
public class k18_receipt03 {

	public static void main(String[] args) {
		// #1. ���� ��ǰ ���� (��ǰ��, ����, �Һ��� ����, �鼼����)
		// ��ǰ��
		String[] k18_itemname = {"���� �Ѷ�� 3kg ������Ʈ","�ƿ�","��ȣ��","���","����",
								"�޹�","ŷ����������","����������10���ڽ�","���������2L","���κ�(200g)",
								"�赿ȫ����� 4��","��� �ع��ȼ����� 5����","�Ե� �㽬�ʰ�ũ���̾Ƹ��","�����(��)120g*20��","������ ��¡�� ���� 900g",
								"���̼� ���尩 6��","�齺�Ǳ⺻���� DARK NAVY","����ǳ�����ǹ�����������","��귣�� ǥ������� 10��","���� �����۸��",
								"ü���ηδߺ�����800g","������(��)","�̸���20kg","������","�ĵ� �׸���ź� �÷���",
								"�ĵ� ����� ġ���","�ĵ� ����� ��ٸ�","����ġ����������(5����)","�йи� ��ǣ�� �Ұ�� �������(���� �ʿ�)","��Ƽ������"};
		ArrayList<String> k18_itemnameTaxFree = new ArrayList<String>(); 	// �鼼 ��ǰ ��ǰ�� �տ� *ǥ�� �߰��� �迭 ����. ���� Ȱ�� ���� ArrayList ���.
		ArrayList<String> k18_itemnamePrint = new ArrayList<String>(); 		// ��ǰ�� ��¿����� ������ �迭 ����. (Byte �Ѱ� �����Ͽ� ��¿����� ���� ����)
		int k18_printLength = 14;											// ����� ��ǰ�� ����(byte) ���� => 14byte (�ѱ� ���� 7����)
		// ��ǰ ���� (���� �ܰ�)
		int[] k18_itemprice = {10000000,1000,990,1200,500,25000,15000,8000,3000,4000,
								2960,2980,4400,4580,6580,8980,29900,5900,1980,4800,
								5580,3280,51990,4770,4480,3900,3900,3950,20800,3980};
		// ��ǰ�� ���� ����
		int[] k18_itemnumber = {1,10,1,5,5,1,2,1,1,1,
								1,1,1,1,1,1,1,1,2,1,
								1,1,1,1,2,2,2,1,1,1};
		// ��ǰ�� �鼼/���� ���� (true = �鼼, false = ����)
		boolean[] k18_taxfree = {true,true,true,true,true,true,true,true,true,true,
								false,false,false,false,false,false,false,false,false,false,
								true,true,true,true,false,false,false,false,true,false};
		
		
		// #3. ��ǰ �� �Һ��� ����, �ΰ���, ���� �� ���� ���
		int k18_totalPrice = 0;					// ���� ���� (�� �Һ��� ����) ���� �� 0���� �ʱ�ȭ.
		int k18_totalNumber = 0;				// ���� ���� (�� ��ǰ ����) ���� �� 0���� �ʱ�ȭ.
		int k18_taxfreeItemTotalPrice = 0;		// ���� ���� (�鼼 ��ǰ ���� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_taxItemTotalPrice = 0;			// ���� ���� (���� ��ǰ �Һ��ڰ��� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_taxItemTotalNetPrice = 0;		// ���� ���� (���� ��ǰ ���� ���� �Ѿ�) ���� �� 0���� �ʱ�ȭ.
		int k18_tax = 0;						// ���� ���� (�� �ΰ���) ���� �� 0���� �ʱ�ȭ.
		float k18_TaxRate = (float)0.1;			// �Ǽ� ���� (���� ����) ���� �� 0.1 (10%)�� �ʱ�ȭ.
		
		// #3-1. ��ǰ �� �Һ��� ����  & �� ���� ���
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			k18_totalPrice += (k18_itemprice[k18_i]*k18_itemnumber[k18_i]); // �� ���� = �� ���� + (��ǰ�� ���� ���� * ����)
		}
		for (int k18_i = 0; k18_i < k18_itemnumber.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			k18_totalNumber += (k18_itemnumber[k18_i]);					// �� ���� = �� ���� + ��ǰ�� ����
		}
		
		// #3-2. �鼼 ��ǰ �Ѿ� & ���� ��ǰ (����/����) �Ѿ� ���
		// (1) �鼼 ��ǰ �Ѿ� (���� 0%�̹Ƿ�, ���� ���� = ���� ����)
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// ��ǰ ������ŭ for�� �ݺ�
			if (k18_taxfree[k18_i] == true) { 							// �鼼 ��ǰ�� ���,
				k18_taxfreeItemTotalPrice += k18_itemprice[k18_i] * k18_itemnumber[k18_i];	// �鼼 ��ǰ �� net price�� ���� ���ϱ�
			} 
		}
		// (2) ���� ��ǰ �Ѿ� (���� ���� ����) = �Һ��ڰ��� �Ѿ� - �鼼��ǰ ���� �Ѿ�;
		k18_taxItemTotalPrice = k18_totalPrice - k18_taxfreeItemTotalPrice;
		// (3) ���� ��ǰ �� �����ݾ� (���� ����) = �鼼��ǰ �Ѿ�/(1+�ΰ�����) => �Ҽ��� �Ʒ� ���� (����)
		k18_taxItemTotalNetPrice = (int)(k18_taxItemTotalPrice/(1+k18_TaxRate)/1*1);

		// #3-3. �ΰ��� ���� ��� = �Һ��ڰ��� �Ѿ� - �鼼��ǰ ���� �Ѿ� - ������ǰ �������� �Ѿ�;
		// *����) ������ǰ ���� ���� ��� �� �Ҽ��� �Ʒ� ����(����)�Ͽ����Ƿ�, �ΰ����� �ݴ�� �Ҽ��� �Ʒ����� �ø� ó���� ���� ��.		
		k18_tax = k18_totalPrice - k18_taxfreeItemTotalPrice - k18_taxItemTotalNetPrice;		
		
		// #3-4. �鼼 ��ǰ *ǥ���ϱ� (�迭�� �Է�)
		for (int k18_i = 0; k18_i < k18_taxfree.length; k18_i++) { // ��ǰ ������ŭ for�� �ݺ�
			if (k18_taxfree[k18_i] == true) { 			// �鼼 ��ǰ�� ���,
				k18_itemnameTaxFree.add("*"); 			// �迭�� * �߰�.
			} else if (k18_taxfree[k18_i] == false) {	// ���� ��ǰ�� ���,
				k18_itemnameTaxFree.add("");			// �迭�� ����("") �߰�.
			}
		}
		
		// #4. ��� ��ǰ�� ���ڼ� �����ϱ� => ��º� �ϴܿ� �޼���(k18_printName) �����Ͽ� ���.
		// ���� Ŭ���� ��üȭ (static �ƴ� �޼��� ��� ����)
		k18_receipt03 k18 = new k18_receipt03();
		// �޼��� ȣ�� �� �μ� �����Ͽ� ��¿� ��ǰ�� ArrayList�� �� ���� 
		// (�ʿ� �μ� : ��ǰ�� �迭, ����(����Ʈ), ��ǰ�� ���� ArrayList)
		k18.k18_printName(k18_itemname, k18_printLength, k18_itemnamePrint);

		// #5. ���� �� �� ������ �޸� ��� ���� DecimalFormat Ŭ���� ��üȭ �� ���� ����
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #6. ���� ��¥&�ð� ��� -> Calendar & SimpleDateFormat ���
		// (1) Calendar�� ��ü ���� �� �ν��Ͻ�ȭ.
		Calendar k18_cal = Calendar.getInstance();
		// (2) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("��������.����.���� �ý�:�к�")
		SimpleDateFormat k18_sdf1 = new SimpleDateFormat ("YYYY-MM-dd HH:mm");
		// (3) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("��������-����-���� �ý�:�к�:����")
		SimpleDateFormat k18_sdf2 = new SimpleDateFormat ("YYYY.MM.dd HH:mm:ss");
		// (4) SimpleDateFormat �� ��ü ���� �� ���� �ʱ�ȭ ("����������������")
		SimpleDateFormat k18_sdf3 = new SimpleDateFormat ("YYYYMMdd");

		// #7. ��� ����
		System.out.printf("%-14s%-10s\n","emart","�̸�Ʈ ������ (031)888-1234");
		System.out.printf("%-14s%-10s\n","","206-86-50913 ����");
		System.out.printf("%-14s%-10s\n","","���� ������ ������� 552");
		System.out.printf("\n");
		System.out.printf("%s\n", "������ �������� ��ȯ/ȯ�� �Ұ�");
		System.out.printf("%s\n", "�����ǰ�� ����, 30�� �̳�(�ż� 7��)");
		System.out.printf("%s\n", "���Ϻ� �귣�� ���� ����(���� ����������)");
		System.out.printf("%s\n", "��ȯ/ȯ�� ���������� ����");
		System.out.printf("\n");
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%-5s%-18s%15s\n", "[������]",k18_sdf1.format(k18_cal.getTime()),"POS:0011-9861");
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		System.out.printf("%2s%-6s%10s%4s%7s\n"," ","�� ǰ ��","�ܡ���","����","�ݡ���");
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		// �ݾ� ��� ��, DecimalFormat ����Ͽ� ���ڿ� �޸� ���� => ���ڿ��� �� ��ȯ�Ǿ� %s �������� ���
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {
			System.out.printf("%-2s%-7s%11s%3s%11s\n", 
								k18_itemnameTaxFree.get(k18_i), 			// �鼼,���� ���� (�鼼 ��ǰ * ���)
								k18_itemnamePrint.get(k18_i),				// ��ǰ��
								k18_comma.format(k18_itemprice[k18_i]),		// �ܰ�
								k18_comma.format(k18_itemnumber[k18_i]),	// ����
								k18_comma.format(k18_itemprice[k18_i]*k18_itemnumber[k18_i])); // �ݾ� (�ܰ� * ����)
			if (k18_i%5 == 4) {		// ��ǰ ����Ʈ �ټ� �ٿ� �� ���� �� ���
				System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
			}
		}
		System.out.printf("%23s%11s\n", "�ѡ�ǰ�񡡼��� ", k18_comma.format(k18_totalNumber));
		System.out.printf("%23s%11s\n", "(*)�顡��������ǰ ", k18_comma.format(k18_taxfreeItemTotalPrice));
		System.out.printf("%23s%11s\n", "������������ǰ ", k18_comma.format(k18_taxItemTotalNetPrice));
		System.out.printf("%23s%11s\n", "�Ρ����������� ", k18_comma.format(k18_tax));
		System.out.printf("%23s%11s\n", "�ա����������� ", k18_comma.format(k18_totalPrice));
		System.out.printf("%-12s%23s\n", "�� �� �� �� �� ��", k18_comma.format(k18_totalPrice));
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		System.out.printf("%-12s%18s%s%s\n", "0012 KEB �ϳ�","541707**0484","/","35860658");
		// ī�� ���� �ݾ� : �� �Һ��ڰ��� ###,### ���·� ��� (DecimalFormat ���)
		System.out.printf("%-12s%9s%s%10s\n", "ī�����(IC)","�Ͻú�"," / ",k18_comma.format(k18_totalPrice)); 
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		System.out.printf("%22s\n", "[�ż�������Ʈ ����]");
		System.out.printf("%-4s%s\n", "ȫ*��","������ ����Ʈ ��Ȳ�Դϴ�.");
		System.out.printf("%-7s%17s%10s\n", "��ȸ�߻�����Ʈ","9350**9995","164");
		System.out.printf("%-9s%14s%1s%9s%1s\n", "����(����)����Ʈ",k18_comma.format(5637),
													"(",k18_comma.format(5473),")");
		System.out.printf("%23s\n", "*�ż�������Ʈ ��ȿ�Ⱓ�� 2���Դϴ�.");
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		System.out.printf("%22s\n", "���űݾױ��� ���������ð� �ڵ��ο�");
		System.out.printf("%-8s%28s\n", "������ȣ : ","34��****");
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%-8s%29s\n", "�����ð� : ",k18_sdf2.format(k18_cal.getTime()));
		System.out.printf("-----------------------------------------\n"); // ��ȣ : 41��
		System.out.printf("%-3s%-6s%s%-6s%22s\n", "ĳ��:","084599"," ","��OO","1150");
		System.out.printf("%22s\n", "������ ���ڵ� ����");
		// ���� ��¥ �� �ð� ��� ��, SimpleDateFormat ����ϸ� Calendar �Լ��� ���� ���� ��¥&�ð� �޾ƿ�.
		System.out.printf("%14s%s\n", k18_sdf3.format(k18_cal.getTime()),"/00119861/00164980/31");	
	}
	
	
	// #4-2. ��� ��ǰ�� ���ڼ� �����ϱ�  : ��ǰ�� ��� Byte�� �°� �ڸ���, ArrayList�� �����ϴ� �Լ�!
	// ���ڼ��� Byte�� �����ϸ�, ��ǰ���� 16����(Byte)�� ��ȯ�Ͽ� ��� ���ذ� ��. 
	// *����: �ѱ��� �� ���ڴ� 2byte�̸� ��� ��������. (����, ����, ��ȣ ���� 1byte & ���)
	// �ʿ��� �μ� : ��ǰ�� �迭, ����(����Ʈ), ��ǰ�� ���� array
	public ArrayList<String> k18_printName (String[] k18_itemName, int k18_byteLenght, ArrayList<String> k18_printName) {
		for (int k18_i = 0; k18_i < k18_itemName.length; k18_i++) { 	// ��ǰ ������ŭ for�� �ݺ�
			
			byte[] k18_byteString = k18_itemName[k18_i].getBytes(); 	// ��ǰ���� 16������ ��ȯ�Ͽ� byte�迭�� ����. (�� ���� BYTE Ȯ�� ����)
			
			if (k18_byteString.length == k18_byteLenght) {				// ����(1) : ��ǰ�� ���� == ��� ���� ����
				k18_printName.add(k18_itemName[k18_i]);			// ���� : ��ǰ�� �״�� ��� -> ��¿� �̸� �迭�� �߰�.
			} 
			else if (k18_byteString.length < k18_byteLenght ) {		// ����(2) : ��ǰ�� ���� < ��� ���� ����
				int k18_diff = k18_byteLenght - k18_byteString.length; // ���� : ����� ��ǰ�� byte���̸�ŭ ���� �߰� (���� = 1byte)
				String k18_blank = "";									// ���� ���� ����Ͽ� �߰��� String ���� ����.
				for (int k18_j = 0; k18_j < k18_diff; k18_j++) {		// ��� byte�� ��ǰ�� byte�� ���� (byte)��ŭ �ݺ�
					k18_blank = k18_blank + " ";						// ����(" " = 1byte) �߰� (���� �� = byte ����)
				}
				k18_printName.add(k18_itemName[k18_i]+k18_blank);	// -> ��¿� ��ǰ�� �迭�� "��ǰ��+����" ����
			} 
			else if (k18_byteString.length > k18_byteLenght) {			// ����(3) : ��ǰ�� ���� > ��� ���� ����
				int k18_minusByteCount = 0;								// ���� : ��ǰ���� �߶���ϴ� ����(byte) ���
				for (int k18_j=0; k18_j<k18_byteLenght; k18_j++) {		// printLength(byte)��ŭ for�� �ݺ�
					if ( k18_byteString[k18_j] < 0) {					// byte ���� ������ ��� = �ѱ��� ���
						k18_minusByteCount += 1;						// minusByteCount +1
					} else {											// byte ���� 0 or ����� ��� = �ѱ��� �ƴ� ���
						k18_minusByteCount += 0;						// minusByteCount ���� ����.
					}
				} 	// for���� �� ���� �� ��, minusByteCount/2 = �ѱ� ���� 
					//-> ���� minusByteCount�� Ȧ�����, �ѱ� �� ���ڰ� �߸��� �Ǵ� ���̹Ƿ�, ��¿� ��ǰ�� Byte�� -1�� �����Ѵ�. 
				if (k18_minusByteCount%2 != 0) {	// 18_minusByteCoun�� Ȧ���� ���,
					k18_printName.add(new String(k18_byteString, 0, k18_byteLenght-1)+" "); 
					// ��� ���� ���̺��� 1byte �۰� ��¿� ��ǰ�� ���� ���� (��� ���� byte - 1;)
					// ���� ����(Byte)�� ���߾� ��ǰ�� �ڸ� ��, String���� ��ȯ�ϰ� ����(1byte) ���ϱ�
					// -> ��¿� �̸� �迭(k18_itemnamePrint)�� ����
				} else {							// 18_minusByteCoun�� ¦���� ���,
					k18_printName.add(new String(k18_byteString, 0, k18_byteLenght)); 
					// ��� ����(Byte)�� ���߾� ��ǰ�� �ڸ� ��, String���� ��ȯ 
					// -> ��¿� �̸� �迭(k18_itemnamePrint)�� ����
				}
			}
		}
		return k18_printName;
	}
	
	
}
