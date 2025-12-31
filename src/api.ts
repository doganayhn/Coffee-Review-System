import { Coffee } from './types';

const API_BASE = (import.meta as any).env?.VITE_API_BASE || '';

async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const res = await fetch(`${API_BASE}${path}`, {
    headers: { 'Content-Type': 'application/json', ...(options?.headers || {}) },
    ...options,
  });

  if (!res.ok) {
    // try to parse json error
    let message = `Request failed (${res.status})`;
    try {
      const body = await res.json();
      message = body?.message || body?.error || message;
      if (body?.fields) {
        const firstKey = Object.keys(body.fields)[0];
        if (firstKey) message = `${firstKey}: ${body.fields[firstKey]}`;
      }
    } catch {
      // ignore
    }
    throw new Error(message);
  }

  return res.json();
}

export async function getCoffees(): Promise<Coffee[]> {
  return request<Coffee[]>('/api/coffees');
}

export async function createReview(input: {
  coffeeId: number;
  author: string;
  rating: number;
  comment: string;
}): Promise<Coffee> {
  return request<Coffee>('/api/reviews', {
    method: 'POST',
    body: JSON.stringify(input),
  });
}
