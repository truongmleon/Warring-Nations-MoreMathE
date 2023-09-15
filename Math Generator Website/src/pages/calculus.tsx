import React from 'react';
import ReactDOM from 'react-dom/client';
import Calculus from '../components/Calculus';
import { useState } from 'react';

ReactDOM.createRoot(document.getElementById('stage-four') as HTMLElement).render(
  <React.StrictMode>
    <Calculus />
  </React.StrictMode>
);
