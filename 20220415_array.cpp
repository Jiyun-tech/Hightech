#include <stdio.h>

int main() {
	
	// 변수 선언 
	int score[20][10];			// [학생 수][과목 수] 
	int inputStudent;			// 학생 수 입력 
	int inputSubject;			// 과목 수 입력
	int StudentSum[20];			// 학생별 총 점수
	float StudentAverage[20]; 	// 학생별 평균 점수
	int SubjectSum[10];			// 과목별 총 점수
	float SubjectAveage[10];	// 과목별 평균점수 
	
	// 입력 
	printf("과목 수 입력 : ");
	scanf("%d", &inputSubject);
	printf("학생 수 입력 : ");
	scanf("%d", &inputStudent);
	
	// 점수 입력 & 배열에 점수 저장
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		printf("Koposw%02d 학생의 점수 %d개를 차례대로 입력하세요. : \n", (indexStudent+1), inputSubject);
		for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
			scanf("%d", &score[indexStudent][indexSubject]);
		}
	}
	
	//계산
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
			StudentSum[indexStudent] += score[indexStudent][indexSubject];
		}
		StudentAverage[indexStudent] =  StudentSum[indexStudent] / (float)inputSubject;
	}
	
	for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
		for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
			SubjectSum[indexSubject] += score[indexStudent][indexSubject];
		}
		SubjectAveage[indexSubject] = SubjectSum[indexSubject] / (float)inputStudent;
	}
	
	// 출력
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		printf("Koposw%2d 학생의 과목 총점은 %d점, 평균은 %f점입니다.\n", (indexStudent+1), StudentSum[indexStudent], StudentAverage[indexStudent]);
	}
	printf("\n");
	for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
		printf("과목%2d 전체 평균 : %f점\n", (indexSubject+1), SubjectAveage[indexSubject]);
	}
	
	
	return 0;
}
