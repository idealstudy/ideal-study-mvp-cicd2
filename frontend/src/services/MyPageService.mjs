import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080', // 기본 URL 설정
    timeout: 5000,                     // 요청 제한 시간 설정
    headers: { 'Content-Type': 'application/json' }
  });

/**
 * 자기소개 조회
 */
export const readBio = async (username, password) => {
  try {
  const response = await apiClient.get('/api/mypage/{userId}/bio');
  return response.data;
  }catch(err){
    console.error('공식프로필조회 API 호출 오류 발생:', err);
  
      // 임시로 더미데이터 반환
      const data = {
        bio: '안녕하세요? 한석원 선생님 수업을 듣다가 이상한과외로 갈아탄 김대민입니다.'
      }
  
      return data;
  }
};

/**
 * 자기소개 수정 - 수정내용 반환필요
 */
export const updateBio = async (userId, bio) => {
  try {
    const response = await apiClient.put(`/api/mypage/${userId}/bio`, {
       bio 
    });

    if (response.ok) {
      alert('내용이 제출되었습니다!');
    } else {
      alert('제출 실패');
    }

    return response.data;
  } catch (err) {
    console.error('자기소개 업데이트 API 호출 오류 발생:', err);

    // 임시로 더미데이터 반환
    const data = {
      bio: '안녕하세요? 한석원 선생님 수업을 듣다가 이상한과외로 갈아탄 김대민입니다. (수정됨)'
    };

    return data;
  }
};