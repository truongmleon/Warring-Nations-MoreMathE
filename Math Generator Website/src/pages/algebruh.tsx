import React from 'react';
import ReactDOM from 'react-dom/client';
import Algebruh from '../components/Algebruh';
import { useState } from 'react';

ReactDOM.createRoot(document.getElementById('stage-three') as HTMLElement).render(
  <React.StrictMode>
    <Algebruh />
  </React.StrictMode>
);
