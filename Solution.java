
package test;

import java.util.ArrayList;
import java.util.List;

//1. reserve 배열에 있는 애들은 무조건 return된다 
//-> 왜냐하면 reserve인 애들은 lost 했더라도 자기걸 입으면 되므로 
//2.reserve, lost에 모두 없는 애들도 return 된다 -> 전체 n명을 기준으로 배열에 없는 애들 찾기 
//-> 여벌이 없지만 잃어버리지도 않은 애들 ->빌릴 필요 x
//3.lost인 애들만 빌려입을 수 있는지 없는지 계산해서 return 여부를 결정한다. 앞으로 비교vs뒷번호와 비

class Solution {

	public int solution(int n, int[] lost, int[] reserve) {
		List<Integer> lostList = new ArrayList<Integer>();
		List<Integer> reserveList = new ArrayList<Integer>();
		List<Integer> tempList = new ArrayList<Integer>();
		List<Integer> returnList = new ArrayList<Integer>(); // 뒷번호에게 빌리기
		List<Integer> returnList2 = new ArrayList<>(); // 앞번호에게 빌리기

		for (int l : lost) {
			lostList.add(l);
		} // arrayList에 추가 

//System.out.println(lostList);
		for (int r : reserve) {
			reserveList.add(r);
		}
//System.out.println(reserveList);

		for (int i = 1; i <= n; i++) {
			returnList.add((Integer) i); // 전체를 추가해놓고 체육복을 구하지 못한 애들을 뺄 것
			returnList2.add((Integer) i); // 앞번호에게 비리는 경우
		}
		// System.out.println(returnList);
		// 제거하기 쉽게 하려고 arrayList를 씀 
		for (Integer l : lostList) {
			for (Integer r : reserveList) {
				if (l.equals(r)) { // 잃어버렸지만 자기 여벌이 있는 애
					tempList.add(l);
					// lostList.remove(l); //loop돌면서 삭제할 경우 conCurrentModificationException이 떨어짐 
					// reserveList.remove(r);
				}
			}
		} // for끝

		for (Integer l : lostList) {
			returnList.remove(l);
			returnList2.remove(l); // 앞번호
		}
		// temp에 있는 애들은 다시 추가
		for (Integer t : tempList) {
			returnList.add(t);
			returnList2.add(t); // 앞번호
			lostList.remove(t); // temp에 있는 애들 lost에서도 제거, 아래 검증을 위해
		}

		// lost의 뒷번호가 reserve에 있는지 검사
		for (Integer l : lostList) {
			int after = (int) l + 1;
			if (reserveList.contains((Integer) after)) {
				returnList.add(l); // 뒷번호에 있으면 return list에 추가
			}
		}

		// lost의 앞번호가 reserve에 있는지 검사
		for (Integer l : lostList) {
			int front = (int) l - 1;
			if (reserveList.contains((Integer) front)) {
				returnList2.add(l); // 뒷번호에 있으면 return list에 추가
			}
		}

		// return size 구하기
		int answer1 = returnList.size(); // 뒷번호
		int answer2 = returnList2.size(); // 앞번호
		System.out.println(returnList);
		System.out.println(returnList2);
		// answer1 과 answer2를 비교해서 리턴할 최종 answer을 선정하기
		if (answer1 == answer2) {
			return answer1;
		} else if (answer1 > answer2) {
			System.out.println("answer1");
			return answer1;
		} else {
			System.out.println("answer2");
			return answer2;
		}
	}// solution끝

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] lost = { 2, 4 };
		int[] reserve = { 2, 3 };
		int answerTest = sol.solution(5, lost, reserve);
		System.out.println(answerTest);
	}
	
	
}