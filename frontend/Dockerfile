# Use a Node.js image
FROM node:18-alpine

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the app
COPY . .

# Build the app
RUN npm run build

# Serve the app with a simple web server
CMD ["npx", "serve", "-s", "build"]

# Expose port
EXPOSE 3000
