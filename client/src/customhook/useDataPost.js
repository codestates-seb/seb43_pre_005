import { useState } from "react";
import axios from "axios";

export function usePostData(url) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const postData = async (postData) => {
    try {
      setLoading(true);
      setError(null);
      const response = await axios.post(url, postData);
      setLoading(false);
      return response.data;
    } catch (error) {
      setLoading(false);
      setError(error);
    }
  };

  return { loading, error, postData };
}
