import React from 'react';
import ReactDOM from 'react-dom/client';
import Arithmetic from '../components/Arithmetic';
import { useState } from 'react';

ReactDOM.createRoot(document.getElementById('stage-one') as HTMLElement).render(
  <React.StrictMode>
    <Arithmetic />
  </React.StrictMode>
);
