package Product;
import java.util.Scanner;

import member.MemberService;
import order.OrderService;
import Product.ProductService;
import Product.ProductVo;

public class Menu {
	private ProductService pService;
	private OrderService oService;
	private MemberService mService;
	
	public Menu() {
		pService = new ProductService();
		oService = new OrderService();
		mService = new MemberService();
	}
	
	public void p_run(Scanner sc) {
		boolean flag=true;
		int m = 0;
		while(flag) {
			System.out.println("1.등록 2.번호로검색 3.이름으로검색 4.수정 5.삭제 6.전체목록 7.종료");
			m = sc.nextInt();
			switch(m) {
			case 1:
				pService.addProduct(sc);
				break;
			case 2:
				pService.getProductByNum(sc);
				break;
			case 3:
				pService.getProductByName(sc);
				break;
			case 4:
				pService.editProduct(sc);
				break;
			case 5:
				pService.delete(sc);
				break;
			case 6:
				pService.printAll();
				break;
			case 7:
				flag = false;
				break;
			}
		}
	}
	
	public void o_run(Scanner sc) {
		boolean flag=true;
		int m = 0, num = 0;
		ProductVo pvo = null;
		while(flag) {
			System.out.println("1.주문 2.내주문목록 3.종료");
			m = sc.nextInt();
			switch(m) {
			case 1:
				System.out.println("=== 주문추가 ===");
				System.out.println("<상품목록>");
				pService.printAll();
				System.out.print("주문할 상품 번호:");
				num = sc.nextInt();
				pvo = pService.getByNum(num);
				if(pvo==null) {
					System.out.println("없는 상품. 주문 취소");
				}else {
					oService.addOrder(sc, pvo);
				}
				break;
			case 2:
				oService.printOrders();
				break;
			case 3:
				flag = false;
				break;
			}
		}
	}
	public void run(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			if (MemberService.loginId == null) {
				System.out.println("1.가입 2.로그인 7.종료");				
			}else {
				System.out.println("3.내정보확인 4.내정보수정 5.로그아웃 6.탈퇴 7.종료 8.쇼핑몰");	
			}
			m = sc.nextInt();
			switch(m) {
			case 1:
				mService.join(sc);
				break;
			case 2:
				mService.login(sc);
				break;
			case 3:
				mService.printMyInfo();
				break;
			case 4:
				mService.editMyInfo(sc);
				break;
			case 5:
				mService.logout();
				break;
			case 6:
				mService.out();
				break;
			case 7:
				flag = false;
				break;
			case 8:
				if(MemberService.loginId.equals("admin")) {
					p_run(sc);//관리자모드(공장 메뉴 실행)
				}else {
					o_run(sc);//일반인모드(구매 메뉴 실행)
				}
				break;
			}
		}
	}
}
