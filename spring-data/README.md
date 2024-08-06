# Spring Transactional Example
* UnexpectedRollbackException
* Owning side

## UnexpectedRollbackException

## Bidirectional and Unidirectional

## Owning Side
Every Bidirectional relationship must have one owning side.
Whenever a bidirectional association is formed, 
the application developer must make sure both sides are in-sync at all times.

The addXXX() and removeXXX() are utility methods that 
synchronize both ends whenever a child element is added or removed.

### One to Many
